/**
 * This module is intended for a JavaFX application and includes
 * the required dependencies and module exports.
 * 
 * @author angel
 */
module edu.commonwealthu.alm2696.CMSC230 { 

    /**
     * Required for JavaFX graphics functionalities.
     */
    requires javafx.graphics;
    
    /**
     * Required for JavaFX UI controls.
     */
    requires javafx.controls;
    
    /**
     * Allows JavaFX reflection-based access to the
     * package for UI components or other reflective operations.
     */
    opens mod03_02 to javafx.graphics;
}
