namespace Client.Model
{
    public class Animal
    {
        public int Id { get; set; }

        public byte[] Picture { get; set; }
        public int Age { get; set; }
        public string AnimalType { get; set; }
        public string Description { get; set; }
        
        public bool Washed { get; set; }
        public bool Fed { get; set; }
        public bool Vaccinated { get; set; }
    }
}