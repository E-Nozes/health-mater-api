package br.com.fiap.healthmater.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * Mapper class for password update.
 *
 * @author Gabriel Oliveira
 */
public class PasswordUpdateDTO {

    @NotEmpty
    @ApiModelProperty(required = true, value = "The user's current password")
    private String oldPassword;

    @NotEmpty
    @ApiModelProperty(required = true, value = "The new password that will replace the current one")
    private String newPassword;

    @NotEmpty
    @ApiModelProperty(required = true, value = "Repeat the new password")
    private String repeatPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

}
