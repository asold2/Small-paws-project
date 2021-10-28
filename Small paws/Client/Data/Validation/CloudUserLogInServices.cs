using System.Net.Http;
using System.Threading.Tasks;

namespace Client.Data.Validation
{
    public class CloudUserLogInServices : IUserLogInServices
    {
        private string uri;
        private readonly HttpClient _httpClient;
        
        public CloudUserLogInServices(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }


        public Task<bool> ValidateUserAsync(string username, string password)
        {
            return null;
        }
    }
}