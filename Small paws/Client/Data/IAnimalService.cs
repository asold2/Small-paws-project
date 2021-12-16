using System.Collections.Generic;
using System.Threading.Tasks;
using Client.Model;

namespace Client.Data
{
    /// <summary>
    /// An animal service interface.
    /// </summary>
    public interface IAnimalService
    {
        /// <summary>
        /// Method used for getting a list of animals through a get request.
        /// </summary>
        /// <returns>a list of animals</returns>
        Task<IList<Animal>> GetAnimalsAsync();
        
        /// <summary>
        /// Method used for adding animals through a post request.
        /// </summary>
        /// <param name="animal">new animal</param>
        Task AddAnimalAsync(Animal animal);
        
        /// <summary>
        /// Method used for updating information about the given animal.
        /// </summary>
        /// <param name="animal">give animal</param>
        Task UpdateAnimal(Animal animal);
    }
}