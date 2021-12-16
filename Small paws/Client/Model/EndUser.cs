using System.Text.Json.Serialization;

namespace Client.Model
{
    /// <summary>
    /// An end user model class.
    /// Each property has a JsonPropertyName specifying how exactly it should look when being send as a json
    /// </summary>
    public class EndUser
    {
        [JsonPropertyName("userId")]
        public int UserId { get; set; }
        [JsonPropertyName("userName")]
        public string UserName { get; set; }
        [JsonPropertyName("password")]
        public string Password { get; set; }
        
        [JsonPropertyName("email")]
        public string Email { get; set; }
        [JsonPropertyName("role")]
        public string Role { get; set; }
        
    }
}