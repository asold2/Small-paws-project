using System;
using System.Collections.Generic;
using System.Security.Claims;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Data.Validation;
using Client.Model;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.JSInterop;

namespace Client.Authentication
{
    public class CustomAuthenticationStateProvider : AuthenticationStateProvider
    {
        private readonly IJSRuntime _jsRuntime;
        private readonly IUserLogInService _logInService;

        private EndUser _cachedUser;

        public CustomAuthenticationStateProvider(IJSRuntime jsRuntime, IUserLogInService logInService)
        {
            _jsRuntime = jsRuntime;
            _logInService = logInService;
        }
        
        public override async Task<AuthenticationState> GetAuthenticationStateAsync()
        {
            var identity = new ClaimsIdentity();
            if (_cachedUser == null)
            {
                var userAsJson = await _jsRuntime.InvokeAsync<string>("sessionStorage.getItem", "currentUser");
                if (!string.IsNullOrEmpty(userAsJson))
                {
                    _cachedUser = JsonSerializer.Deserialize<EndUser>(userAsJson);
                    identity = SetupClaimsForUser(_cachedUser);
                }
            }
            else
            {
                identity = SetupClaimsForUser(_cachedUser);
            }

            ClaimsPrincipal cachedClaimsPrincipal = new ClaimsPrincipal(identity);
            return await Task.FromResult(new AuthenticationState(cachedClaimsPrincipal));
        }
        
        public async Task ValidateLogin(string username, string password)
        {
            Console.WriteLine("Validating log in");
            if (string.IsNullOrEmpty(username)) throw new Exception("Enter username");
            if (string.IsNullOrEmpty(password)) throw new Exception("Enter password");
            var user = await _logInService.ValidateUserAsync(username, password);
            var identity = SetupClaimsForUser(user);
                
            var serialisedData = JsonSerializer.Serialize(user);
            await _jsRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", serialisedData);
            _cachedUser = user;

            NotifyAuthenticationStateChanged(
                Task.FromResult(new AuthenticationState(new ClaimsPrincipal(identity))));
        }
        
        public async Task Logout()
        {
            _cachedUser = null;
            var user = new ClaimsPrincipal(new ClaimsIdentity());
            await _jsRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", "");
            NotifyAuthenticationStateChanged(Task.FromResult(new AuthenticationState(user)));
        }

        public EndUser GetCachedUser()
        {
            return _cachedUser;
        }

        private ClaimsIdentity SetupClaimsForUser(EndUser endUser)
        {
            var claims = new List<Claim> {new("Role",  endUser.Role)};
            //Add a claim to check for the type of the subclass(EndUser)
            var identity = new ClaimsIdentity(claims, "apiauth_type");
            return identity;
        }
    }
}