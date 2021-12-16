using System.Text.Json.Serialization;
// ReSharper disable PropertyCanBeMadeInitOnly.Global

namespace Client.Model
{
    /// <summary>
    /// An animal model class.
    /// Each property has a JsonPropertyName specifying how exactly it should look when being send as a json
    /// </summary>
    public class Animal
    {
        [JsonPropertyName("id")]
        public int Id { get; init; }

        [JsonPropertyName("picture")]
        public byte[] Picture { get; set; }
        
        [JsonPropertyName("age")]
        public int Age { get; set; }
        
        [JsonPropertyName("sex")]
        public string Sex { get; set; }
        
        [JsonPropertyName("animalType")]
        public string AnimalType { get; init; }
        
        [JsonPropertyName("description")]
        public string Description { get; set; }
        
        [JsonPropertyName("washed")]
        public bool Washed { get; set; }
        
        [JsonPropertyName("fed")]
        public bool Fed { get; set; }
        
        [JsonPropertyName("vaccinated")]
        public bool Vaccinated { get; set; }

        [JsonPropertyName("healthNotes")] public string HealthNotes { get; set; }
    }
}