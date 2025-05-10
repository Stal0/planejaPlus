package com.stal0.planejaPlus.mappers;

import com.stal0.planejaPlus.dto.UserDTO;
import com.stal0.planejaPlus.dto.WalletDTO;
import com.stal0.planejaPlus.entities.User;
import com.stal0.planejaPlus.entities.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletDTO walletToDTO(Wallet wallet);

    Wallet DtoToWallet(WalletDTO walletDTO);

    void copyDtoToWallet(WalletDTO walletDTO, Wallet wallet);
}
