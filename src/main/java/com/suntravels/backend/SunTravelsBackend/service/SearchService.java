package com.suntravels.backend.SunTravelsBackend.service;

import com.suntravels.backend.SunTravelsBackend.DTO.GuestDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRespond;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRoomDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRoomTypeRespond;
import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.ContractRoom;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRepository;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRoomRepository;
import com.suntravels.backend.SunTravelsBackend.repository.MarkupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SearchService
{
    private final ContractRepository contractRepository;
    private final ContractRoomRepository contractRoomRepository;
    private final MarkupRepository markupRepository;

    public SearchService( ContractRepository contractRepository, ContractRoomRepository contractRoomRepository, MarkupRepository markupRepository )
    {
        this.contractRepository = contractRepository;
        this.contractRoomRepository = contractRoomRepository;
        this.markupRepository = markupRepository;
    }

    public List<SearchRespond> searchRooms( SearchRoomDTO searchRoomDTO ){

        // Get the last added markup value
        float markupPercentage = markupRepository.findTopByOrderByMarkupIDDesc().getMarkupPercentage();

        List<SearchRespond> searchResponds = new ArrayList<>();

        // Sort the Search GuestDTO object according to adults count reversed
        List<GuestDTO> guestDTOS = searchRoomDTO.getGuests();
        guestDTOS.sort( Comparator.comparingInt( GuestDTO::getAdults ).reversed() );

        // Add number of nights to the checkIn date
        LocalDate checkInDate = searchRoomDTO.getCheckInDate();
        int numberOfNights = searchRoomDTO.getNumberOfNights();

        LocalDate checkOutDate = checkInDate.plusDays( numberOfNights );

        // Get all the contract between the check in date and check out date
        List<Contract> contracts = contractRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual( checkInDate, checkOutDate );

        List<Contract> suitableContracts = new ArrayList<>();

        // Get the contracts that can fullfiling the search rooms
        for (Contract contract: contracts){

            // get the contract rooms that belongs to the contract get it order by max adults
            List<ContractRoom> contractRooms = contractRoomRepository.findByContractOrderByMaxAdultsDesc( contract );

            // contract is suitable to give search rooms
            boolean suitable = true;
            // total number of rooms given
            int totalRoomNumber = 0;
            for ( GuestDTO guestDTO: guestDTOS){

                // add wanted number of rooms that in search to total number of rooms
                totalRoomNumber += guestDTO.getRooms();
                // number of success rooms to the search
                int numberSuccessRooms = 0;

                // get the total number of success rooms by iterate on it
                for ( ContractRoom contractRoom: contractRooms){
                    if ( contractRoom.getMaxAdults() >= guestDTO.getAdults()){
                        numberSuccessRooms += contractRoom.getNumberOfRooms();
                    }
                }

                // if success room less than wanted rooms then break that contract
                if (numberSuccessRooms < guestDTO.getRooms()){
                    suitable = false;
                    break;
                }
                if (( numberSuccessRooms - totalRoomNumber ) < 0 ){
                    suitable = false;
                }
            }
            // if that contract give wanted rooms then add that contract to suitable contract array
            if (suitable){
                suitableContracts.add( contract );
            }
        }
        // iterate suitable contract array for find the correct prices
        for ( Contract suitableContract : suitableContracts ){
            // get the contract room of suitable contract order by max adults
            List<ContractRoom> suitableContractRooms = contractRoomRepository.findByContractOrderByMaxAdults( suitableContract );

            // order search derails array by max adults
            List<GuestDTO> suitableGuestDTOS = searchRoomDTO.getGuests();
            suitableGuestDTOS.sort( Comparator.comparingInt( GuestDTO::getAdults ) );

            float totalPrice = 0f;
            List<SearchRoomTypeRespond> searchRoomTypeRespondsArray = new ArrayList<>();

            // Add contract room properties to tha array
            List<Integer> numberOfAdultsArray = new ArrayList<>();
            List<Integer> numberOfRoomsArray = new ArrayList<>();
            List<Float>  priceArray = new ArrayList<>();
            List<String> roomTypesArray = new ArrayList<>();

            for ( ContractRoom suitableContractRoom: suitableContractRooms){
                numberOfAdultsArray.add( suitableContractRoom.getMaxAdults() );
                numberOfRoomsArray.add( suitableContractRoom.getNumberOfRooms() );
                priceArray.add( suitableContractRoom.getPrice() );
                roomTypesArray.add( suitableContractRoom.getRoomType().getRoomTypeName() );
            }

            // get the suitable guestDTO iterate
            for ( GuestDTO suitableGuestDTO: suitableGuestDTOS) {


                int i = 0;
                int remainingRooms = suitableGuestDTO.getRooms();

                for ( Integer numberOfAdults: numberOfAdultsArray ) {
                    if (numberOfAdults >= suitableGuestDTO.getAdults()) {
                        break;
                    }
                    i += 1;
                }
                // this one runs when wanted rooms can give the same number of max adults rooms
                for ( int j=i; j<numberOfRoomsArray.size(); j++ ) {

                    if( numberOfRoomsArray.get( j ) >= remainingRooms ) {

                        numberOfRoomsArray.set( j, ( numberOfRoomsArray.get( j ) - remainingRooms ) );

                        float price = remainingRooms * numberOfAdultsArray.get( i ) * priceArray.get( j ) * ( markupPercentage + 100 )/100;
                        totalPrice += price;

                        SearchRoomTypeRespond searchRoomTypeRespond = new SearchRoomTypeRespond(
                                roomTypesArray.get( j ),
                                remainingRooms,
                                numberOfAdultsArray.get( i ),
                                numberOfAdultsArray.get( j ),
                                price);

                        searchRoomTypeRespondsArray.add( searchRoomTypeRespond );

                        break;
                    }
                    // this one runs when wanted rooms can give higher number of max adults rooms
                    else {
                        remainingRooms -= numberOfRoomsArray.get( j );
                        int haveToMet = numberOfRoomsArray.get( j );
                        numberOfRoomsArray.set( j, 0 );

                        float price = haveToMet * numberOfAdultsArray.get( i ) * priceArray.get( j ) * (markupPercentage + 100 )/100;
                        totalPrice += price;

                        SearchRoomTypeRespond searchRoomTypeRespond = new SearchRoomTypeRespond(
                                roomTypesArray.get( j ),
                                haveToMet,
                                numberOfAdultsArray.get( i ),
                                numberOfAdultsArray.get( j ),
                                price
                        );

                        searchRoomTypeRespondsArray.add( searchRoomTypeRespond );

                    }
                }
            }
            // Create the Search Response
            SearchRespond searchRespond = new SearchRespond(
                    suitableContract.getHotel().getHotelName(),
                    totalPrice,
                    searchRoomTypeRespondsArray);

            searchResponds.add( searchRespond );

        }
        return searchResponds;
    }
}
