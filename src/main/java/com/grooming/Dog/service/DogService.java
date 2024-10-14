package com.grooming.Dog.service;



import com.grooming.Dog.dto.DogDto;
import com.grooming.Dog.model.Dog;
import com.grooming.Dog.model.Owner;
import com.grooming.Dog.repository.DogRepository;
import com.grooming.Dog.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private OwnerRepository ownerRepository;


    public Dog registerDog(DogDto dogDto) {

        Owner owner = ownerRepository.findById(dogDto.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Dog dog = new Dog();
        dog.setName(dogDto.name());
        dog.setAge(dogDto.age());
        dog.setOwner(owner);

        return dogRepository.save(dog);
    }

    //Get all dogs
    public List<Dog> getDogs() {
        return dogRepository.findAll();
    }

    //Get dog by Id
    public Dog getDogsId(Long id) {
        return dogRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Dog updateDog(Long id, DogDto dogDto) {
        Dog updateDog = dogRepository.findById(id).orElseThrow(RuntimeException::new);
        Owner owner = ownerRepository.findById(dogDto.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Dog not found"));

        updateDog.setName(dogDto.name());
        updateDog.setAge(dogDto.age());
        updateDog.setOwner(owner);

        return dogRepository.save(updateDog);
    }

    public Dog deleteDog(Long id) {
        Dog deleteDog = dogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The dog doesn't exist, ID: " + id));

        dogRepository.deleteById(deleteDog.getId());
        return deleteDog;
    }
}
