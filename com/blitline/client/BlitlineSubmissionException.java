/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blitline.client;

/**
 * Blitline exception wrapper for submissions.
 * 
 * @author Blitline Developer
 */
public class BlitlineSubmissionException extends Exception {
    public BlitlineSubmissionException(String message) {
        super(message);
    }
}