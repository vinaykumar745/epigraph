/* The following code was generated by JFlex 1.4.3 on 4/18/16 11:22 AM */

package com.sumologic.dohyo.plugin.schema.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static com.sumologic.dohyo.plugin.schema.lexer.SchemaElementTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/18/16 11:22 AM from the specification file
 * <tt>/Users/konstantin/workspace/dohyo/project/schema-editor/src/com/sumologic/dohyo/plugin/schema/lexer/SchemaLexer.flex</tt>
 */
public class SchemaLexer implements FlexLexer {
  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\3\1\3\1\1\22\0\1\3\1\0\1\10"+
    "\4\0\1\6\2\0\1\5\1\43\1\41\1\0\1\40\1\4\12\12"+
    "\1\37\2\0\1\42\3\0\32\11\1\46\1\7\1\47\3\0\1\22"+
    "\1\36\1\25\1\26\1\23\1\27\1\35\1\34\1\13\2\11\1\31"+
    "\1\14\1\21\1\16\1\15\1\11\1\17\1\24\1\20\1\30\1\11"+
    "\1\33\1\32\2\11\1\44\1\0\1\45\54\0\1\11\12\0\1\11"+
    "\4\0\1\11\5\0\27\11\1\0\37\11\1\0\u01ca\11\4\0\14\11"+
    "\16\0\5\11\7\0\1\11\1\0\1\11\201\0\5\11\1\0\2\11"+
    "\2\0\4\11\10\0\1\11\1\0\3\11\1\0\1\11\1\0\24\11"+
    "\1\0\123\11\1\0\213\11\10\0\236\11\11\0\46\11\2\0\1\11"+
    "\7\0\47\11\110\0\33\11\5\0\3\11\55\0\53\11\25\0\12\12"+
    "\4\0\2\11\1\0\143\11\1\0\1\11\17\0\2\11\7\0\2\11"+
    "\12\12\3\11\2\0\1\11\20\0\1\11\1\0\36\11\35\0\131\11"+
    "\13\0\1\11\16\0\12\12\41\11\11\0\2\11\4\0\1\11\5\0"+
    "\26\11\4\0\1\11\11\0\1\11\3\0\1\11\27\0\31\11\107\0"+
    "\1\11\1\0\13\11\127\0\66\11\3\0\1\11\22\0\1\11\7\0"+
    "\12\11\4\0\12\12\1\0\7\11\1\0\7\11\5\0\10\11\2\0"+
    "\2\11\2\0\26\11\1\0\7\11\1\0\1\11\3\0\4\11\3\0"+
    "\1\11\20\0\1\11\15\0\2\11\1\0\3\11\4\0\12\12\2\11"+
    "\23\0\6\11\4\0\2\11\2\0\26\11\1\0\7\11\1\0\2\11"+
    "\1\0\2\11\1\0\2\11\37\0\4\11\1\0\1\11\7\0\12\12"+
    "\2\0\3\11\20\0\11\11\1\0\3\11\1\0\26\11\1\0\7\11"+
    "\1\0\2\11\1\0\5\11\3\0\1\11\22\0\1\11\17\0\2\11"+
    "\4\0\12\12\25\0\10\11\2\0\2\11\2\0\26\11\1\0\7\11"+
    "\1\0\2\11\1\0\5\11\3\0\1\11\36\0\2\11\1\0\3\11"+
    "\4\0\12\12\1\0\1\11\21\0\1\11\1\0\6\11\3\0\3\11"+
    "\1\0\4\11\3\0\2\11\1\0\1\11\1\0\2\11\3\0\2\11"+
    "\3\0\3\11\3\0\14\11\26\0\1\11\25\0\12\12\25\0\10\11"+
    "\1\0\3\11\1\0\27\11\1\0\12\11\1\0\5\11\3\0\1\11"+
    "\32\0\2\11\6\0\2\11\4\0\12\12\25\0\10\11\1\0\3\11"+
    "\1\0\27\11\1\0\12\11\1\0\5\11\3\0\1\11\40\0\1\11"+
    "\1\0\2\11\4\0\12\12\1\0\2\11\22\0\10\11\1\0\3\11"+
    "\1\0\51\11\2\0\1\11\20\0\1\11\21\0\2\11\4\0\12\12"+
    "\12\0\6\11\5\0\22\11\3\0\30\11\1\0\11\11\1\0\1\11"+
    "\2\0\7\11\72\0\60\11\1\0\2\11\14\0\7\11\11\0\12\12"+
    "\47\0\2\11\1\0\1\11\2\0\2\11\1\0\1\11\2\0\1\11"+
    "\6\0\4\11\1\0\7\11\1\0\3\11\1\0\1\11\1\0\1\11"+
    "\2\0\2\11\1\0\4\11\1\0\2\11\11\0\1\11\2\0\5\11"+
    "\1\0\1\11\11\0\12\12\2\0\4\11\40\0\1\11\37\0\12\12"+
    "\26\0\10\11\1\0\44\11\33\0\5\11\163\0\53\11\24\0\1\11"+
    "\12\12\6\0\6\11\4\0\4\11\3\0\1\11\3\0\2\11\7\0"+
    "\3\11\4\0\15\11\14\0\1\11\1\0\12\12\6\0\46\11\1\0"+
    "\1\11\5\0\1\11\2\0\53\11\1\0\u014d\11\1\0\4\11\2\0"+
    "\7\11\1\0\1\11\1\0\4\11\2\0\51\11\1\0\4\11\2\0"+
    "\41\11\1\0\4\11\2\0\7\11\1\0\1\11\1\0\4\11\2\0"+
    "\17\11\1\0\71\11\1\0\4\11\2\0\103\11\45\0\20\11\20\0"+
    "\125\11\14\0\u026c\11\2\0\21\11\1\0\32\11\5\0\113\11\25\0"+
    "\15\11\1\0\4\11\16\0\22\11\16\0\22\11\16\0\15\11\1\0"+
    "\3\11\17\0\64\11\43\0\1\11\4\0\1\11\3\0\12\12\46\0"+
    "\12\12\6\0\130\11\10\0\51\11\1\0\1\11\5\0\106\11\12\0"+
    "\35\11\51\0\12\12\36\11\2\0\5\11\13\0\54\11\25\0\7\11"+
    "\10\0\12\12\46\0\27\11\11\0\65\11\53\0\12\12\6\0\12\12"+
    "\15\0\1\11\135\0\57\11\21\0\7\11\4\0\12\12\51\0\36\11"+
    "\15\0\2\11\12\12\54\11\32\0\44\11\34\0\12\12\3\0\3\11"+
    "\12\12\44\11\153\0\4\11\1\0\4\11\3\0\2\11\11\0\300\11"+
    "\100\0\u0116\11\2\0\6\11\2\0\46\11\2\0\6\11\2\0\10\11"+
    "\1\0\1\11\1\0\1\11\1\0\1\11\1\0\37\11\2\0\65\11"+
    "\1\0\7\11\1\0\1\11\3\0\3\11\1\0\7\11\3\0\4\11"+
    "\2\0\6\11\4\0\15\11\5\0\3\11\1\0\7\11\164\0\1\11"+
    "\15\0\1\11\20\0\15\11\145\0\1\11\4\0\1\11\2\0\12\11"+
    "\1\0\1\11\3\0\5\11\6\0\1\11\1\0\1\11\1\0\1\11"+
    "\1\0\4\11\1\0\13\11\2\0\4\11\5\0\5\11\4\0\1\11"+
    "\64\0\2\11\u0a7b\0\57\11\1\0\57\11\1\0\205\11\6\0\4\11"+
    "\3\0\2\11\14\0\46\11\1\0\1\11\5\0\1\11\2\0\70\11"+
    "\7\0\1\11\20\0\27\11\11\0\7\11\1\0\7\11\1\0\7\11"+
    "\1\0\7\11\1\0\7\11\1\0\7\11\1\0\7\11\1\0\7\11"+
    "\120\0\1\11\u01d5\0\2\11\52\0\5\11\5\0\2\11\4\0\126\11"+
    "\6\0\3\11\1\0\132\11\1\0\4\11\5\0\51\11\3\0\136\11"+
    "\21\0\33\11\65\0\20\11\u0200\0\u19b6\11\112\0\u51cd\11\63\0\u048d\11"+
    "\103\0\56\11\2\0\u010d\11\3\0\20\11\12\12\2\11\24\0\57\11"+
    "\20\0\31\11\10\0\106\11\61\0\11\11\2\0\147\11\2\0\4\11"+
    "\1\0\4\11\14\0\13\11\115\0\12\11\1\0\3\11\1\0\4\11"+
    "\1\0\27\11\35\0\64\11\16\0\62\11\34\0\12\12\30\0\6\11"+
    "\3\0\1\11\4\0\12\12\34\11\12\0\27\11\31\0\35\11\7\0"+
    "\57\11\34\0\1\11\12\12\46\0\51\11\27\0\3\11\1\0\10\11"+
    "\4\0\12\12\6\0\27\11\3\0\1\11\5\0\60\11\1\0\1\11"+
    "\3\0\2\11\2\0\5\11\2\0\1\11\1\0\1\11\30\0\3\11"+
    "\2\0\13\11\7\0\3\11\14\0\6\11\2\0\6\11\2\0\6\11"+
    "\11\0\7\11\1\0\7\11\221\0\43\11\15\0\12\12\6\0\u2ba4\11"+
    "\14\0\27\11\4\0\61\11\u2104\0\u016e\11\2\0\152\11\46\0\7\11"+
    "\14\0\5\11\5\0\1\11\1\0\12\11\1\0\15\11\1\0\5\11"+
    "\1\0\1\11\1\0\2\11\1\0\2\11\1\0\154\11\41\0\u016b\11"+
    "\22\0\100\11\2\0\66\11\50\0\14\11\164\0\5\11\1\0\207\11"+
    "\23\0\12\12\7\0\32\11\6\0\32\11\13\0\131\11\3\0\6\11"+
    "\2\0\6\11\2\0\6\11\2\0\3\11\43\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\3\1\14\3\1\4\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\0\1\17\3\0\22\3\1\16\2\3\1\20\17\3"+
    "\1\16\2\3\1\21\3\3\1\22\6\3\1\23\1\24"+
    "\1\25\3\3\1\26\7\3\1\27\1\3\1\30\1\3"+
    "\1\31\2\3\1\32\1\3\1\33\2\3\1\34\1\3"+
    "\1\35\1\3\1\36\1\37\2\3\1\40\1\3\1\41"+
    "\1\42";

  private static int [] zzUnpackAction() {
    int [] result = new int[123];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\170\0\240\0\310\0\360\0\u0118"+
    "\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\u0208\0\u0230\0\u0258"+
    "\0\u0280\0\u02a8\0\50\0\50\0\50\0\50\0\50\0\50"+
    "\0\50\0\50\0\50\0\u02d0\0\u02f8\0\240\0\50\0\u0320"+
    "\0\310\0\u0348\0\u0370\0\u0398\0\u03c0\0\u03e8\0\u0410\0\u0438"+
    "\0\u0460\0\u0488\0\u04b0\0\u04d8\0\u0500\0\u0528\0\u0550\0\u0578"+
    "\0\u05a0\0\u05c8\0\u05f0\0\u0618\0\u0640\0\u0668\0\u0690\0\360"+
    "\0\u06b8\0\u06e0\0\u0708\0\u0730\0\u0758\0\u0780\0\u07a8\0\u07d0"+
    "\0\u07f8\0\u0820\0\u0848\0\u0870\0\u0898\0\u08c0\0\u08e8\0\50"+
    "\0\u0910\0\u0938\0\360\0\u0960\0\u0988\0\u09b0\0\360\0\u09d8"+
    "\0\u0a00\0\u0a28\0\u0a50\0\u0a78\0\u0aa0\0\360\0\360\0\360"+
    "\0\u0ac8\0\u0af0\0\u0b18\0\360\0\u0b40\0\u0b68\0\u0b90\0\u0bb8"+
    "\0\u0be0\0\u0c08\0\u0c30\0\360\0\u0c58\0\360\0\u0c80\0\360"+
    "\0\u0ca8\0\u0cd0\0\360\0\u0cf8\0\360\0\u0d20\0\u0d48\0\360"+
    "\0\u0d70\0\360\0\u0d98\0\360\0\360\0\u0dc0\0\u0de8\0\360"+
    "\0\u0e10\0\u0e38\0\360";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[123];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\3\3\1\4\1\2\1\5\1\2\1\6\1\7"+
    "\1\2\1\10\1\11\2\7\1\12\1\7\1\13\1\7"+
    "\1\14\1\15\1\7\1\16\1\7\1\17\1\20\1\7"+
    "\1\21\2\7\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\51\0\3\3\50\0\1\34"+
    "\1\35\42\0\6\36\1\37\1\40\40\36\7\41\1\42"+
    "\1\37\37\41\11\0\26\7\22\0\3\7\1\43\4\7"+
    "\1\44\15\7\22\0\11\7\1\45\1\46\4\7\1\47"+
    "\6\7\22\0\12\7\1\50\13\7\22\0\11\7\1\51"+
    "\14\7\22\0\10\7\1\52\10\7\1\53\4\7\22\0"+
    "\7\7\1\54\7\7\1\55\6\7\22\0\5\7\1\56"+
    "\4\7\1\57\13\7\22\0\10\7\1\60\15\7\22\0"+
    "\2\7\1\61\2\7\1\62\20\7\22\0\2\7\1\63"+
    "\23\7\22\0\5\7\1\64\20\7\11\0\1\34\2\0"+
    "\45\34\5\35\1\65\42\35\2\36\1\0\45\36\2\41"+
    "\1\0\45\41\11\0\4\7\1\66\21\7\22\0\7\7"+
    "\1\67\16\7\22\0\4\7\1\70\21\7\22\0\7\7"+
    "\1\71\16\7\22\0\20\7\1\72\5\7\22\0\14\7"+
    "\1\73\11\7\22\0\3\7\1\74\22\7\22\0\17\7"+
    "\1\75\6\7\22\0\7\7\1\76\16\7\22\0\6\7"+
    "\1\77\17\7\22\0\4\7\1\100\21\7\22\0\17\7"+
    "\1\101\6\7\22\0\16\7\1\102\7\7\22\0\2\7"+
    "\1\103\23\7\22\0\13\7\1\104\12\7\22\0\10\7"+
    "\1\105\15\7\22\0\7\7\1\106\16\7\22\0\5\7"+
    "\1\107\20\7\11\0\4\35\1\110\1\65\42\35\11\0"+
    "\5\7\1\111\20\7\22\0\12\7\1\112\13\7\22\0"+
    "\11\7\1\113\14\7\22\0\7\7\1\114\16\7\22\0"+
    "\5\7\1\115\20\7\22\0\12\7\1\116\13\7\22\0"+
    "\3\7\1\117\22\7\22\0\12\7\1\120\13\7\22\0"+
    "\2\7\1\121\23\7\22\0\4\7\1\122\21\7\22\0"+
    "\25\7\1\123\22\0\11\7\1\124\14\7\22\0\5\7"+
    "\1\125\20\7\22\0\7\7\1\126\16\7\22\0\24\7"+
    "\1\127\1\7\22\0\23\7\1\130\2\7\22\0\20\7"+
    "\1\131\5\7\22\0\6\7\1\132\17\7\22\0\24\7"+
    "\1\133\1\7\22\0\2\7\1\134\23\7\22\0\6\7"+
    "\1\135\17\7\22\0\13\7\1\136\12\7\22\0\10\7"+
    "\1\137\15\7\22\0\10\7\1\140\15\7\22\0\20\7"+
    "\1\141\5\7\22\0\20\7\1\142\5\7\22\0\17\7"+
    "\1\143\6\7\22\0\10\7\1\144\15\7\22\0\12\7"+
    "\1\145\13\7\22\0\7\7\1\146\16\7\22\0\12\7"+
    "\1\147\13\7\22\0\15\7\1\150\10\7\22\0\4\7"+
    "\1\151\21\7\22\0\15\7\1\152\10\7\22\0\24\7"+
    "\1\153\1\7\22\0\12\7\1\154\13\7\22\0\12\7"+
    "\1\155\13\7\22\0\20\7\1\156\5\7\22\0\11\7"+
    "\1\157\14\7\22\0\6\7\1\160\17\7\22\0\11\7"+
    "\1\161\14\7\22\0\13\7\1\162\12\7\22\0\3\7"+
    "\1\163\22\7\22\0\7\7\1\164\16\7\22\0\10\7"+
    "\1\165\15\7\22\0\14\7\1\166\11\7\22\0\12\7"+
    "\1\167\13\7\22\0\12\7\1\170\13\7\22\0\10\7"+
    "\1\171\15\7\22\0\7\7\1\172\16\7\22\0\13\7"+
    "\1\173\12\7\11\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3680];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;
  private static final char[] EMPTY_BUFFER = new char[0];
  private static final int YYEOF = -1;
  private static java.io.Reader zzReader = null; // Fake

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\20\1\11\11\2\1\1\0\1\11\3\0"+
    "\45\1\1\11\63\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[123];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** this buffer may contains the current text array to be matched when it is cheap to acquire it */
  private char[] zzBufferArray;

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */
  int curlyCount = 0;

  public SchemaLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public SchemaLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 1694) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState){
    zzBuffer = buffer;
    zzBufferArray = com.intellij.util.text.CharArrayUtil.fromSequenceWithoutCopying(buffer);
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzPushbackPos = 0;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBufferArray != null ? zzBufferArray[zzStartRead+pos]:zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;
    char[] zzBufferArrayL = zzBufferArray;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL)
            zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = (zzBufferArrayL != null ? zzBufferArrayL[zzCurrentPosL++] : zzBufferL.charAt(zzCurrentPosL++));
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 24: 
          { return curlyCount == 0 ? S_IMPORT : S_ID;
          }
        case 35: break;
        case 4: 
          { return S_COLON;
          }
        case 36: break;
        case 31: 
          { return curlyCount == 0 ? S_BOOLEAN_T : S_ID;
          }
        case 37: break;
        case 14: 
          { return S_BLOCK_COMMENT;
          }
        case 38: break;
        case 22: 
          { return curlyCount == 0 ? S_MULTI : S_ID;
          }
        case 39: break;
        case 5: 
          { return S_DOT;
          }
        case 40: break;
        case 28: 
          { return curlyCount == 0 ? S_INTEGER_T : S_ID;
          }
        case 41: break;
        case 26: 
          { return curlyCount == 0 ? S_STRING_T : S_ID;
          }
        case 42: break;
        case 1: 
          { return com.intellij.psi.TokenType.BAD_CHARACTER;
          }
        case 43: break;
        case 3: 
          { return S_ID;
          }
        case 44: break;
        case 34: 
          { return curlyCount == 0 ? S_SUPPLEMENTS : S_ID;
          }
        case 45: break;
        case 8: 
          { return S_PLUS;
          }
        case 46: break;
        case 27: 
          { return curlyCount == 0 ? S_DOUBLE_T : S_ID;
          }
        case 47: break;
        case 20: 
          { return curlyCount == 0 ? S_LONG_T : S_ID;
          }
        case 48: break;
        case 7: 
          { return S_EQ;
          }
        case 49: break;
        case 21: 
          { return curlyCount == 0 ? S_WITH : S_ID;
          }
        case 50: break;
        case 11: 
          { return S_BRACKET_LEFT;
          }
        case 51: break;
        case 16: 
          { return curlyCount < 2 ? S_MAP : S_ID;
          }
        case 52: break;
        case 32: 
          { return curlyCount == 0 ? S_NAMESPACE : S_ID;
          }
        case 53: break;
        case 19: 
          { return curlyCount < 2 ? S_LIST : S_ID;
          }
        case 54: break;
        case 15: 
          { return S_STRING;
          }
        case 55: break;
        case 29: 
          { return curlyCount == 0 ? S_EXTENDS : S_ID;
          }
        case 56: break;
        case 6: 
          { return S_COMMA;
          }
        case 57: break;
        case 23: 
          { return curlyCount == 0 ? S_UNION : S_ID;
          }
        case 58: break;
        case 18: 
          { return curlyCount == 0 ? S_ENUM : S_ID;
          }
        case 59: break;
        case 13: 
          { return S_COMMENT;
          }
        case 60: break;
        case 30: 
          { return curlyCount < 2 ? S_DEFAULT : S_ID;
          }
        case 61: break;
        case 17: 
          { return curlyCount == 0 ? S_META : S_ID;
          }
        case 62: break;
        case 25: 
          { return curlyCount == 0 ? S_RECORD : S_ID;
          }
        case 63: break;
        case 9: 
          { curlyCount++; return S_CURLY_LEFT;
          }
        case 64: break;
        case 33: 
          { return curlyCount == 0 ? S_SUPPLEMENT : S_ID;
          }
        case 65: break;
        case 10: 
          { curlyCount = (curlyCount == 0 ? 0 : curlyCount - 1) ; return S_CURLY_RIGHT;
          }
        case 66: break;
        case 12: 
          { return S_BRACKET_RIGHT;
          }
        case 67: break;
        case 2: 
          { return com.intellij.psi.TokenType.WHITE_SPACE;
          }
        case 68: break;
        default:
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          }
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
