using System.Text.Json.Serialization;

namespace Client.Model
{
    public class EndUser
    {
        private int Id { get; set; }
        [JsonPropertyName("userName")]
        public string UserName { get; set; }
        [JsonPropertyName("password")]
        public string Password { get; set; }
        
        [JsonPropertyName("email")]
        public string Email { get; set; }
    }
}