using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Registration
{
    /// <summary>
    /// A Create account service interface
    /// </summary>
    public interface IUserCreateAccountService
    {
        /// <summary>
        /// Method used for making a post request for creating a new user account.
        /// </summary>
        /// <param name="petOwner">Created pet owner account</param>
        /// <returns>status code of created or not created</returns>
        Task<int> CreateUserAsync(PetOwner petOwner);
        
        /// <summary>
        /// Method used for getting a list of all existing users through a get request.
        /// </summary>
        /// <returns>A list of all users</returns>
        Task<IList<EndUser>> GetUsersAsync();
        
        /// <summary>
        /// Method used for checking if the typed in username already exists. 
        /// </summary>
        /// <param name="userName">Given username</param>
        /// <returns>Status code, depending on if the username exists or not</returns>
        Task<int> CheckUserName(string userName);
    }
}