using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data
{
    public interface IDataLoader
    {
        Task<IList<Animal>> GetAnimalsAsync();
    }
}