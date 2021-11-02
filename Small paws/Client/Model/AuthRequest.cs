using System.Text.Json.Serialization;

namespace Client.Model
{
    public class AuthRequest
    {
        
        [JsonPropertyName("userName")]
        public string Username { get; set; }

        [JsonPropertyName("password")]

        public string Password { get; set; }
        
        [JsonPropertyName("role")]

        public string Role { get; set; }
    }
}