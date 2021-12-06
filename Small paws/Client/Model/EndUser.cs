using System.Text.Json.Serialization;

namespace Client.Model
{
    public class EndUser
    {
        [JsonPropertyName("userId")]
        public int userId { get; set; }
        [JsonPropertyName("userName")]
        public string UserName { get; set; }
        [JsonPropertyName("password")]
        public string Password { get; set; }
        
        [JsonPropertyName("email")]
        public string Email { get; set; }
        [JsonPropertyName("role")]
        public string Role { get; set; }

        //public string Email { get; set; }
    }
}