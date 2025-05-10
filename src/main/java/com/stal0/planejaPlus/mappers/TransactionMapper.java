package com.stal0.planejaPlus.mappers;

import com.stal0.planejaPlus.dto.TransactionDTO;
import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.entities.Transaction;
import com.stal0.planejaPlus.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO transactionToDTO(Transaction transaction);

    Transaction DtoToTransaction(TransactionDTO transactionDTO);

    void copyDtoToTransaction(TransactionDTO transactionDTO, Transaction transaction);
}
