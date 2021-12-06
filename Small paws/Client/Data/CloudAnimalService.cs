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
        private const string Uri = "http://localhost:8090";
        private readonly HttpClient _httpClient;
        public CloudAnimalService()
        {
            _httpClient = new HttpClient();
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

        public async Task UpdateAnimal(Animal newAnimal)
        {
            var animalAsJson = JsonSerializer.Serialize(newAnimal);
            HttpContent httpContent = new StringContent(
                animalAsJson,
                Encoding.UTF8,
                "application/json");
            await _httpClient.PatchAsync(Uri + "/new_information", httpContent);
     
        }
    }
}