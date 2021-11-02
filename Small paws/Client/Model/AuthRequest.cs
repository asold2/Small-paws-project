namespace Client.Model
{
    public class AuthRequest
    {
        public string Username { get; set; }
        public string Password { get; set; }
        
        public string Role { get; set; }
    }
}