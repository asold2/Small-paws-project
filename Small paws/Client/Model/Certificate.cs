using System.Text.Json.Serialization;

namespace Client.Model
{
    /// <summary>
    /// A certificate model class.
    /// Each property has a JsonPropertyName specifying how exactly it should look when being send as a json
    /// </summary>
    
    // ReSharper disable once ClassNeverInstantiated.Global
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