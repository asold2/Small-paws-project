using System.Collections;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data
{
    public class CloudDataLoader : IDataLoader
    {
        private string uri;
        private readonly HttpClient _httpClient;
        public CloudDataLoader(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<IList<Animal>> GetAnimalsAsync()
        {
            return null;
        }
    }
}