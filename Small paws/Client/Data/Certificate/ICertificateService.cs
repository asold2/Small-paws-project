using System.Collections.Generic;
using System.Threading.Tasks;

namespace Client.Data.Certificate
{
    public interface ICertificateService
    {
        Task<IList<Model.Certificate>> GetCertificatesByUserId(int id);
    }
}