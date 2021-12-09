using System.Text.Json.Serialization;

namespace Client.Model
{
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

        [JsonPropertyName("healthNotes")] public string healthNotes { get; set; }
    }
}