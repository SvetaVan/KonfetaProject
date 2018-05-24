package binaryTree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class BracketsTest {
    @Test
    public void bracketsTest() {
        {
            char[] chars = {'[','{','('};
            Assert.assertFalse("Check not even length",Validation2.validation(Arrays.asList('[','{','(')));
        }
       /* {
            char[] chars = new char[0];
            Assert.assertFalse("there is 0 elements",Validation2.validation(chars));
        }*/
       /* {
            char[] chars = {'(','i'};
            Assert.assertFalse("there is invalid elements",Validation2.validation(Arrays.asList('(','i')));
        }*/
        {
            char[] chars = {'[','{','(','}'};
            Assert.assertFalse("incorrect sequence",Validation2.validation(Arrays.asList('[','{','(','}')));
        }
        {
            char[] chars = {'[','{','(',')','}',']'};
            Assert.assertTrue("correct sequence",Validation2.validation(Arrays.asList('[','{','(',')','}',']')));
        }
        {
            char[] chars = {'[','{','}','{','}',']'};
            Assert.assertTrue("correct sequence",Validation2.validation(Arrays.asList('[','{','}','{','}',']')));
        }
        {
            char[] chars = {'[',']','}','{','}',']'};
            Assert.assertFalse("incorrect sequence",Validation2.validation(Arrays.asList('[',']','}','{','}',']')));
        }


    }
}
