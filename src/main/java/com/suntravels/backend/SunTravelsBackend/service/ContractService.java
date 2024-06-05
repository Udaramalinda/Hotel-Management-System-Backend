package com.suntravels.backend.SunTravelsBackend.service;

import com.suntravels.backend.SunTravelsBackend.DTO.ContractDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractResponse;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractRoomDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractRoomResponse;
import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.ContractRoom;
import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRepository;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRoomRepository;
import com.suntravels.backend.SunTravelsBackend.repository.HotelRepository;
import com.suntravels.backend.SunTravelsBackend.repository.RoomTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService
{
    private final ContractRepository contractRepository;
    private final ContractRoomRepository contractRoomRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;

    public ContractService(
            ContractRepository contractRepository,
            ContractRoomRepository contractRoomRepository,
            HotelRepository hotelRepository,
            RoomTypeRepository roomTypeRepository ){

        this.contractRepository = contractRepository;
        this.contractRoomRepository = contractRoomRepository;
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
    }


    @Transactional
    public String addNewContract( ContractDTO contractDTO ){

        Hotel hotel = hotelRepository.getHotelByHotelName( contractDTO.getHotelName() );
        // Get the contracts that belongs to that hotel
        List<Contract> contractList = contractRepository.getContractByHotel( hotel );
        // if contract array is empty then directly add the contract
        if ( contractList.isEmpty() ) {

            Contract contract = new Contract(hotel, contractDTO.getStartDate(), contractDTO.getEndDate());
            contractRepository.save( contract );

            for ( ContractRoomDTO contractRoomDTO: contractDTO.getRoomDetails() ){
                RoomType roomType = roomTypeRepository.getRoomTypeByRoomTypeName( contractRoomDTO.getRoomTypeName());
                ContractRoom contractRoom = new ContractRoom(contract, roomType, contractRoomDTO.getPrice(), contractRoomDTO.getNumOfRooms(), contractRoomDTO.getMaxAdults());
                contractRoomRepository.save( contractRoom );
            }
        }

        else {
            // if array is not empty then check any hotel contract having the same time period of entering contract
            boolean haveContractOnThatPeriod = true;
            for ( Contract selectedContract: contractList ) {

                if ( !((contractDTO.getStartDate().isBefore( selectedContract.getStartDate() ) && contractDTO.getEndDate().isBefore( selectedContract.getStartDate() ))
                || (contractDTO.getStartDate().isAfter( selectedContract.getEndDate() ) && contractDTO.getEndDate().isAfter( selectedContract.getEndDate() ))) ){
                    haveContractOnThatPeriod = false;
                }

            }
            // if that time period not having contract on that hotel then add that contract
            if (haveContractOnThatPeriod){

                Contract contract = new Contract(hotel, contractDTO.getStartDate(), contractDTO.getEndDate());
                contractRepository.save( contract );

                for ( ContractRoomDTO contractRoomDTO: contractDTO.getRoomDetails() ){
                    RoomType roomType = roomTypeRepository.getRoomTypeByRoomTypeName( contractRoomDTO.getRoomTypeName());
                    ContractRoom contractRoom = new ContractRoom(contract, roomType, contractRoomDTO.getPrice(), contractRoomDTO.getNumOfRooms(), contractRoomDTO.getMaxAdults());
                    contractRoomRepository.save( contractRoom );
                }
            }
            // if not contract doesn't save
            else {
                return "Hotel Has Contract On That Time Period";
            }
        }

        return "Contract Save Successfully";

    }

    public List<ContractResponse> getContract( String hotelName ){

        List<ContractResponse> contractResponses = new ArrayList<>();

        Hotel hotel = hotelRepository.getHotelByHotelName( hotelName );
        // Get all the contracts of the selected hotel
        List<Contract> contracts = contractRepository.getContractByHotel( hotel );

        // iterate the contracts and add that contract according to the contract respond
        for (Contract contract: contracts){
            List<ContractRoom> contractRooms = contractRoomRepository.getContractRoomByContract( contract );
            List<ContractRoomResponse> contractRoomResponses = new ArrayList<>();

            for (ContractRoom contractRoom: contractRooms){

                // Add the contract respond according to the contracts rooms
                ContractRoomResponse contractRoomResponse = new ContractRoomResponse(
                        contractRoom.getRoomType().getRoomTypeName(),
                        contractRoom.getPrice(),
                        contractRoom.getNumberOfRooms(),
                        contractRoom.getMaxAdults()
                );
                // add contract room respond to array
                contractRoomResponses.add( contractRoomResponse );
            }

            ContractResponse contractResponse = new ContractResponse(
                    contract.getHotel().getHotelName(),
                    contract.getStartDate(),
                    contract.getEndDate(),
                    contractRoomResponses
            );
            // add contract to the contract respond array
            contractResponses.add( contractResponse );
        }

        return contractResponses;
    }
}
