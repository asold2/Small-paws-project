using System.Threading.Tasks;

namespace Client.Data.Validation
{
    public interface IUserLogInService
    {
        Task<int> ValidateUserAsync(string username, string password);
    }
}