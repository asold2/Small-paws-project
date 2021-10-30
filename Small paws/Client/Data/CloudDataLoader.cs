using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data
{
    public class CloudDataLoader : IDataLoader
    {
        private const string Uri = "https://localhost:8090";
        private readonly HttpClient _httpClient;
        public CloudDataLoader(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IList<Animal>> GetAnimalsAsync()
        {
            var responseMessage = await _httpClient.GetAsync(Uri + "/animals");
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception("Ups something went wrong");
            }

            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<List<Animal>>(message);
            return result;
        }
    }
}