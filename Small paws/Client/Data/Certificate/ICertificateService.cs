using System.Collections.Generic;
using System.Threading.Tasks;

namespace Client.Data.Certificate
{
    /// <summary>
    /// A certificate service interface
    /// </summary>
    public interface ICertificateService
    {
        /// <summary>
        /// Method used for getting a list of existing certificates for the specific user through a get request.
        /// </summary>
        /// <param name="id">Id of the user</param>
        /// <returns>A list of certificates for the user</returns>
        Task<IList<Model.Certificate>> GetCertificatesByUserId(int id);
    }
}