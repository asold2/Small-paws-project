using System.Text.Json.Serialization;

namespace Client.Model
{
    public class Certificate
    {
        [JsonPropertyName("id")]
        public int Id { get; set; }
        [JsonPropertyName("requestId")]
        public AdoptionRequest RequestId { get; set; }
        [JsonPropertyName("document")]
        public byte[] Document { get; set; }
        [JsonPropertyName("petOwnerId")]
        public PetOwner PetOwnerId { get; set; }

    }
}