using System.Threading.Tasks;
using Client.Model;

namespace Client.Data.Registration
{
    public interface IUserCreateAccountServices
    {
        Task<int> CreateUserAsync(PetOwner petOwner);
    }
}