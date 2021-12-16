using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data
{
    public class CloudAnimalService : IAnimalService
    {
        /// <summary>
        /// Uri of the 2nd tier server.
        /// </summary>
        private const string Uri = "http://localhost:8090";
        /// <summary>
        /// HttpClient used for making http requests to the 2nd tier server.
        /// </summary>
        private readonly HttpClient _httpClient;
        
        /// <summary>
        /// Constructor dependency injection.
        /// </summary>
        public CloudAnimalService()
        {
            _httpClient = new HttpClient();
        }

        /// <inheritdoc />
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

        /// <inheritdoc />
        public async Task AddAnimalAsync(Animal animal)
        {
            var animalAsJson = JsonSerializer.Serialize(animal);
            HttpContent httpContent = new StringContent(
                animalAsJson,
                Encoding.UTF8,
                "application/json");
            var responseMessage = await _httpClient.PostAsync(Uri + "/animal", httpContent);
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception($"Error, {responseMessage.StatusCode}, {responseMessage.ReasonPhrase}");
            } 
        }

        /// <inheritdoc />
        public async Task UpdateAnimal(Animal animal)
        {
            var animalAsJson = JsonSerializer.Serialize(animal);
            HttpContent httpContent = new StringContent(
                animalAsJson,
                Encoding.UTF8,
                "application/json");
            await _httpClient.PatchAsync(Uri + "/new_information", httpContent);
     
        }
    }
}