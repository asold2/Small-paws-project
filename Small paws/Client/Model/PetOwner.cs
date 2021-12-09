using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Client.Model
{
    public class PetOwner : EndUser
    {

        [JsonPropertyName("id")]
        public int Id { get; set; }
        
        [JsonPropertyName("firstName")]
        public string FirstName { get; set; }
        
        [JsonPropertyName("lastName")]
        public string LastName { get; set; }
        
        [JsonPropertyName("age")]
        public int Age { get; set; }
        
        [JsonPropertyName("sex")]
        public string Sex { get; set; }
        
        [JsonPropertyName("familyStatus")]
        public string FamilyStatus { get; set; }
        
        [JsonPropertyName("avgIncome")]
        public int AvgIncome { get; set; }
        
        [JsonPropertyName("address")]
        public string Address { get; set; }

        [JsonPropertyName("zipcode")]
        public int ZipCode { get; set; }
        
        [JsonPropertyName("jobTitle")]
        public string JobTitle { get; set; }
        

        // public List<AdoptionRequest> myRequests { get; set; }

    }
}