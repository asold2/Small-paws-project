using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Validation
{
    public interface IUserLogInService
    {
        Task<EndUser> ValidateUserAsync(string username, string password);
    }
}