﻿using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Registration
{
    public class CloudUserCreateAccountServices : IUserCreateAccountServices
    {
        private readonly HttpClient _httpClient;
        private const string Uri = "https://localhost:8090";

        public CloudUserCreateAccountServices(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }


        public async Task<bool> CreateUserAsync(PetOwner petOwner)
        { 
           
            var userAsJson = JsonSerializer.Serialize(petOwner);
            HttpContent httpContent = new StringContent(
                userAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/users", httpContent);
            
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            }
            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<bool>(message);
            return result;
        }
    }
}