using System;
using System.Diagnostics.CodeAnalysis;
using System.Text.Json.Serialization;
// ReSharper disable PropertyCanBeMadeInitOnly.Global

namespace Client.Model
{
    /// <summary>
    /// An adoption request model class.
    /// Each property has a JsonPropertyName specifying how exactly it should look when being send as a json
    /// </summary>
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