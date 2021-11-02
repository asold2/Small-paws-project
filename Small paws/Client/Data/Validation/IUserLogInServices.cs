using System.Threading.Tasks;

namespace Client.Data.Validation
{
    public interface IUserLogInServices
    {
        Task<int> ValidateUserAsync(string username, string password, string role);
    }
}