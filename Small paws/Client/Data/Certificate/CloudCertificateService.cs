using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Certificate
{
    public class CloudCertificateService : ICertificateService
    {
        
        private const string Uri = "http://localhost:8090";
        private readonly HttpClient _httpClient;

        public CloudCertificateService()
        {
            this._httpClient = new HttpClient();
        }

        public async Task<IList<Model.Certificate>> GetCertificatesByUserId(int id)
        {
            var responseMessage = await _httpClient.GetAsync(Uri + "/certificates/"+id);
            if (!responseMessage.IsSuccessStatusCode)
            {
                throw new Exception("Ups something went wrong");
            }

            var message = await responseMessage.Content.ReadAsStringAsync();
            var result = JsonSerializer.Deserialize<List<Model.Certificate>>(message);
            return result;
        }
    }
}