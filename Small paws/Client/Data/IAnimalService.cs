using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data
{
    public interface IAnimalService
    {
        Task<IList<Animal>> GetAnimalsAsync();
        Task AddAnimalAsync(Animal animal);
        Task UpdateAnimal(Animal newAnimal);
    }
}