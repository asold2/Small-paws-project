using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Validation
{
    /// <summary>
    /// A login service interface
    /// </summary>
    public interface IUserLogInService
    {
        /// <summary>
        /// Method used for validating login information.
        /// </summary>
        /// <param name="username">Given username</param>
        /// <param name="password">Given password</param>
        /// <returns>An object of an EndUser will all information if successfully validated</returns>
        Task<EndUser> ValidateUserAsync(string username, string password);
    }
}