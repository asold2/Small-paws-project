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
    /// <summary>
    /// A class used for authenticating logged in user.
    /// </summary>
    public class CustomAuthenticationStateProvider : AuthenticationStateProvider
    {
        
        /// <summary>
        /// Private field for JavaScript runtime.
        /// Specifically used for invoking an asynchronous JavaScript function.
        /// </summary>
        private readonly IJSRuntime _jsRuntime;
        /// <summary>
        /// A service used for validating the user.
        /// </summary>
        private readonly IUserLogInService _logInService;
        /// <summary>
        /// An EndUser field used for caching logged in user.
        /// </summary>
        private EndUser _cachedUser;

        /// <summary>
        /// Constructor dependency injection.
        /// </summary>
        /// <param name="jsRuntime"></param>
        /// <param name="logInService"></param>
        public CustomAuthenticationStateProvider(IJSRuntime jsRuntime, IUserLogInService logInService)
        {
            _jsRuntime = jsRuntime;
            _logInService = logInService;
        }
        
        /// <summary>
        /// Method for getting an authentication state asynchronously.
        /// </summary>
        /// <returns>AuthenticationState</returns>
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

            var cachedClaimsPrincipal = new ClaimsPrincipal(identity);
            return await Task.FromResult(new AuthenticationState(cachedClaimsPrincipal));
        }
        
        /// <summary>
        /// Method used for validating login information.
        /// </summary>
        /// <param name="username">typed in username value</param>
        /// <param name="password">typed in password value</param>
        /// <exception cref="Exception"></exception>
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
        
        /// <summary>
        /// Method used for deleting the cached user when logging out.
        /// </summary>
        public async Task Logout()
        {
            _cachedUser = null;
            var user = new ClaimsPrincipal(new ClaimsIdentity());
            await _jsRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", "");
            NotifyAuthenticationStateChanged(Task.FromResult(new AuthenticationState(user)));
        }

        /// <summary>
        /// Method used for getting logged in user.
        /// </summary>
        /// <returns>logged in user</returns>
        public EndUser GetCachedUser()
        {
            return _cachedUser;
        }

        /// <summary>
        /// Method used for checking user's role(identity).
        /// </summary>
        /// <param name="endUser">logged in user</param>
        /// <returns>identity of the logged in user</returns>
        private ClaimsIdentity SetupClaimsForUser(EndUser endUser)
        {
            var claims = new List<Claim> {new("Role",  endUser.Role)};
            //Add a claim to check for the type of the subclass(EndUser)
            var identity = new ClaimsIdentity(claims, "apiauth_type");
            return identity;
        }
    }
}