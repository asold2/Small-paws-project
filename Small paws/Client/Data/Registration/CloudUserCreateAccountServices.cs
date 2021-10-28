using System.Net.Http;
using System.Threading.Tasks;

namespace Client.Data.Registration
{
    public class CloudUserCreateAccountServices : IUserCreateAccountServices
    {
        private readonly HttpClient _httpClient;
        private string uri;
        
        public CloudUserCreateAccountServices(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }


        public async Task CreateUserAsync()
        {
        }
    }
}