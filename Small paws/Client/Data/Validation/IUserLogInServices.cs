using System.Threading.Tasks;

namespace Client.Data.Validation
{
    public interface IUserLogInServices
    {
        Task<bool> ValidateUserAsync(string username, string password, string role);
    }
}