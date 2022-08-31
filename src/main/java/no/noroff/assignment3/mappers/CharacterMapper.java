package no.noroff.assignment3.mappers;


import no.noroff.assignment3.moduls.dtos.CharacterDTO;
import org.mapstruct.Mapper;
import no.noroff.assignment3.moduls.Character;

@Mapper(componentModel = "spring")
public interface CharacterMapper {


    CharacterDTO characterToCharacterDTO(Character character);
}
