using System;
using System.Diagnostics.CodeAnalysis;
using System.Text.Json.Serialization;

namespace Client.Model
{
    public class AdoptionRequest
    {
        [JsonPropertyName("requestId")] 
        public int RequestId { get; set; }
        [JsonPropertyName("date")]
        public DateTime DateTime { get; set; }
        [JsonPropertyName("animalId")]
        public Animal AnimalId { get; set; }
        [JsonPropertyName("userId")]
        public PetOwner UserId { get; set; }
        [JsonPropertyName("veterinarianId"), AllowNull]
        public Veterinarian VeterinarianId { get; set; }
        [JsonPropertyName("approve")]
        public bool Approve { get; set; }

        [JsonPropertyName("animalName")]
        public string AnimalName { get; set; }

    }
}