/* The following code was generated by JFlex 1.7.0-SNAPSHOT tweaked for IntelliJ platform */

package com.sumologic.epigraph.schema.parser.lexer;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static io.epigraph.lang.lexer.EpigraphElementTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0-SNAPSHOT
 * from the specification file <tt>SchemaLexer.flex</tt>
 */
public class SchemaLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

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
   * Chosen bits are [11, 6, 4]
   * Total runtime size is 13696 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[(ZZ_CMAP_Z[ch>>10]<<6)|((ch>>4)&0x3f)]<<4)|(ch&0xf)];
  }

  /* The ZZ_CMAP_Z table has 1088 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2\11\1\12\1\13\6\14\1\15\23\14\1\16"+
    "\1\14\1\17\1\20\12\14\1\21\10\11\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1"+
    "\32\1\11\1\33\1\34\2\11\1\14\1\35\3\11\1\36\10\11\1\37\1\40\20\11\1\41\2\11"+
    "\1\42\5\11\1\43\4\11\1\44\1\45\4\11\51\14\1\46\3\14\1\47\1\50\4\14\1\51\12"+
    "\11\1\52\u0381\11");

  /* The ZZ_CMAP_Y table has 2752 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\2\1\1\10\1\11\1\12\1\13\1\12\1\13\34\12\1"+
    "\14\1\15\1\16\10\1\1\17\1\20\1\12\1\21\4\12\1\22\10\12\1\23\12\12\1\4\1\12"+
    "\1\24\1\4\1\12\1\25\4\1\1\12\1\26\1\27\2\1\2\12\1\26\1\1\1\30\1\4\5\12\1\31"+
    "\1\32\1\33\1\1\1\34\1\12\1\1\1\35\5\12\1\36\1\37\1\40\1\12\1\26\1\41\1\12"+
    "\1\42\1\43\1\1\1\12\1\44\4\1\1\12\1\45\4\1\1\46\2\12\1\47\1\1\1\50\1\51\1"+
    "\4\1\52\1\53\1\54\1\55\1\56\1\57\1\51\1\15\1\60\1\53\1\54\1\61\1\1\1\62\1"+
    "\63\1\64\1\65\1\21\1\54\1\66\1\1\1\67\1\51\1\70\1\71\1\53\1\54\1\66\1\1\1"+
    "\57\1\51\1\37\1\72\1\73\1\74\1\75\1\1\1\67\1\63\1\1\1\76\1\34\1\54\1\47\1"+
    "\1\1\77\1\51\1\1\1\76\1\34\1\54\1\100\1\1\1\56\1\51\1\101\1\76\1\34\1\12\1"+
    "\102\1\56\1\103\1\51\1\104\1\105\1\106\1\12\1\107\1\110\1\1\1\63\1\1\1\4\2"+
    "\12\1\111\1\110\1\112\2\1\1\113\1\114\1\115\1\116\1\117\1\120\2\1\1\67\1\1"+
    "\1\112\1\1\1\121\1\12\1\122\1\1\1\123\7\1\2\12\1\26\1\103\1\112\1\124\1\125"+
    "\1\126\1\127\1\112\2\12\1\130\2\12\1\131\24\12\1\132\1\133\2\12\1\132\2\12"+
    "\1\134\1\135\1\13\3\12\1\135\3\12\1\26\2\1\1\12\1\1\5\12\1\136\1\4\45\12\1"+
    "\137\1\12\1\4\1\26\4\12\1\26\1\140\1\141\1\15\1\12\1\15\1\12\1\15\1\141\1"+
    "\67\3\12\1\142\1\1\1\143\1\112\2\1\1\112\5\12\1\25\2\12\1\144\4\12\1\36\1"+
    "\12\1\145\2\1\1\63\1\12\1\146\1\45\2\12\1\147\1\12\1\75\1\112\2\1\1\12\1\110"+
    "\3\12\1\45\2\1\2\112\1\150\5\1\1\105\2\12\1\142\1\151\1\112\2\1\1\152\1\12"+
    "\1\153\1\40\2\12\1\36\1\1\2\12\1\142\1\1\1\154\1\40\1\12\1\146\6\1\1\155\1"+
    "\156\14\12\4\1\21\12\1\136\2\12\1\136\1\157\1\12\1\146\3\12\1\160\1\161\1"+
    "\162\1\122\1\161\7\1\1\163\1\1\1\122\6\1\1\164\1\165\1\166\1\167\1\170\3\1"+
    "\1\171\147\1\2\12\1\145\2\12\1\145\10\12\1\172\1\173\2\12\1\130\3\12\1\174"+
    "\1\1\1\12\1\110\4\175\4\1\1\103\35\1\1\176\2\1\1\177\1\4\4\12\1\200\1\4\4"+
    "\12\1\131\1\105\1\12\1\146\1\4\4\12\1\145\1\1\1\12\1\26\3\1\1\12\40\1\133"+
    "\12\1\36\4\1\135\12\1\36\2\1\10\12\1\122\4\1\2\12\1\146\20\12\1\122\1\12\1"+
    "\201\1\1\2\12\1\145\1\103\1\12\1\146\4\12\1\36\2\1\1\202\1\203\5\12\1\204"+
    "\1\12\1\146\1\25\3\1\1\202\1\205\1\12\1\27\1\1\3\12\1\142\1\203\2\12\1\142"+
    "\1\1\1\112\1\1\1\206\1\40\1\12\1\36\1\12\1\110\1\1\1\12\1\122\1\46\2\12\1"+
    "\27\1\103\1\112\1\207\1\210\2\12\1\44\1\1\1\211\1\112\1\12\1\212\3\12\1\213"+
    "\1\214\1\215\1\26\1\64\1\216\1\217\1\175\2\12\1\131\1\36\7\12\1\27\1\112\72"+
    "\12\1\142\1\12\1\220\2\12\1\147\20\1\26\12\1\146\6\12\1\75\2\1\1\110\1\221"+
    "\1\54\1\222\1\223\6\12\1\15\1\1\1\152\25\12\1\146\1\1\4\12\1\203\2\12\1\25"+
    "\2\1\1\147\7\1\1\207\7\12\1\122\1\1\1\112\1\4\1\26\1\4\1\26\1\224\4\12\1\145"+
    "\1\225\1\226\2\1\1\227\1\12\1\13\1\230\2\146\2\1\7\12\1\26\30\1\1\12\1\122"+
    "\3\12\1\67\2\1\2\12\1\1\1\12\1\231\2\12\1\36\1\12\1\146\2\12\1\232\3\1\11"+
    "\12\1\146\1\112\5\1\2\12\1\25\3\12\1\142\11\1\23\12\1\110\1\12\1\36\1\25\11"+
    "\1\1\233\2\12\1\234\1\12\1\36\1\12\1\110\1\12\1\145\4\1\1\12\1\235\1\12\1"+
    "\36\1\12\1\75\4\1\3\12\1\236\4\1\1\67\1\237\1\12\1\142\2\1\1\12\1\122\1\12"+
    "\1\122\2\1\1\121\1\12\1\45\1\1\3\12\1\36\1\12\1\36\1\12\1\27\1\12\1\15\6\1"+
    "\4\12\1\44\3\1\3\12\1\27\3\12\1\27\60\1\1\152\2\12\1\25\2\1\1\63\1\1\1\152"+
    "\2\12\2\1\1\12\1\44\1\112\1\152\1\12\1\110\1\63\1\1\2\12\1\240\1\152\2\12"+
    "\1\27\1\241\1\242\2\1\1\12\1\21\1\147\5\1\1\243\1\244\1\44\2\12\1\145\1\1"+
    "\1\112\1\71\1\53\1\54\1\66\1\1\1\245\1\15\21\1\3\12\1\1\1\246\1\112\12\1\2"+
    "\12\1\145\2\1\1\247\2\1\3\12\1\1\1\250\1\112\2\1\2\12\1\26\1\1\1\112\3\1\1"+
    "\12\1\75\1\1\1\112\26\1\4\12\1\112\1\103\34\1\3\12\1\44\20\1\71\12\1\75\16"+
    "\1\14\12\1\142\53\1\2\12\1\145\75\1\44\12\1\110\33\1\43\12\1\44\1\12\1\145"+
    "\1\112\6\1\1\12\1\146\1\1\3\12\1\1\1\142\1\112\1\152\1\251\1\12\67\1\4\12"+
    "\1\45\1\67\3\1\1\152\6\1\1\15\77\1\6\12\1\26\1\122\1\44\1\75\66\1\5\12\1\207"+
    "\3\12\1\141\1\252\1\253\1\254\3\12\1\255\1\256\1\12\1\257\1\260\1\34\24\12"+
    "\1\261\1\12\1\34\1\131\1\12\1\131\1\12\1\207\1\12\1\207\1\145\1\12\1\145\1"+
    "\12\1\54\1\12\1\54\1\12\1\262\3\263\14\12\1\45\123\1\1\254\1\12\1\264\1\265"+
    "\1\266\1\267\1\270\1\271\1\272\1\147\1\273\1\147\24\1\55\12\1\110\2\1\103"+
    "\12\1\45\15\12\1\146\150\12\1\15\25\1\41\12\1\146\36\1");

  /* The ZZ_CMAP_A table has 3008 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\2\1\1\2\2\1\1\22\0\1\2\1\0\1\5\5\0\1\51\1\52\1\4\1\0\1\43\1\0\1\13"+
    "\1\3\12\11\1\42\1\0\1\53\1\44\1\54\2\0\32\14\1\47\1\6\1\50\2\0\1\15\1\25\1"+
    "\7\1\30\1\31\1\26\1\32\1\41\1\36\1\16\2\14\1\33\1\17\1\24\1\21\1\20\1\14\1"+
    "\22\1\27\1\23\1\10\1\37\1\35\1\34\1\40\1\14\1\45\1\0\1\46\14\0\1\14\12\0\1"+
    "\14\4\0\1\14\5\0\27\14\1\0\12\14\4\0\14\14\16\0\5\14\7\0\1\14\1\0\1\14\1\0"+
    "\5\14\1\0\2\14\2\0\4\14\1\0\1\14\6\0\1\14\1\0\3\14\1\0\1\14\1\0\4\14\1\0\23"+
    "\14\1\0\13\14\10\0\15\14\2\0\1\14\6\0\10\14\10\0\13\14\5\0\3\14\15\0\12\12"+
    "\4\0\6\14\1\0\1\14\17\0\2\14\7\0\2\14\12\12\3\14\2\0\2\14\1\0\16\14\15\0\11"+
    "\14\13\0\1\14\16\0\12\12\6\14\4\0\2\14\4\0\1\14\5\0\6\14\4\0\1\14\11\0\1\14"+
    "\3\0\1\14\7\0\11\14\7\0\5\14\17\0\26\14\3\0\1\14\2\0\1\14\7\0\12\14\4\0\12"+
    "\12\1\14\4\0\10\14\2\0\2\14\2\0\26\14\1\0\7\14\1\0\1\14\3\0\4\14\3\0\1\14"+
    "\20\0\1\14\15\0\2\14\1\0\1\14\5\0\6\14\4\0\2\14\1\0\2\14\1\0\2\14\1\0\2\14"+
    "\17\0\4\14\1\0\1\14\7\0\12\12\2\0\3\14\20\0\11\14\1\0\2\14\1\0\2\14\1\0\5"+
    "\14\3\0\1\14\2\0\1\14\30\0\1\14\13\0\10\14\2\0\1\14\3\0\1\14\1\0\6\14\3\0"+
    "\3\14\1\0\4\14\3\0\2\14\1\0\1\14\1\0\2\14\3\0\2\14\3\0\3\14\3\0\14\14\13\0"+
    "\10\14\1\0\2\14\10\0\3\14\5\0\4\14\1\0\5\14\3\0\1\14\3\0\2\14\15\0\13\14\2"+
    "\0\1\14\21\0\1\14\12\0\6\14\5\0\22\14\3\0\10\14\1\0\11\14\1\0\1\14\2\0\7\14"+
    "\11\0\1\14\1\0\2\14\14\0\12\12\7\0\2\14\1\0\1\14\2\0\2\14\1\0\1\14\2\0\1\14"+
    "\6\0\4\14\1\0\7\14\1\0\3\14\1\0\1\14\1\0\1\14\2\0\2\14\1\0\4\14\1\0\2\14\11"+
    "\0\1\14\2\0\5\14\1\0\1\14\11\0\12\12\2\0\14\14\1\0\24\14\13\0\5\14\3\0\6\14"+
    "\4\0\4\14\3\0\1\14\3\0\2\14\7\0\3\14\4\0\15\14\14\0\1\14\1\0\6\14\1\0\1\14"+
    "\5\0\1\14\2\0\13\14\1\0\15\14\1\0\4\14\2\0\7\14\1\0\1\14\1\0\4\14\2\0\1\14"+
    "\1\0\4\14\2\0\7\14\1\0\1\14\1\0\4\14\2\0\16\14\2\0\6\14\2\0\15\14\2\0\1\14"+
    "\1\0\10\14\7\0\15\14\1\0\6\14\23\0\1\14\4\0\1\14\3\0\11\14\1\0\1\14\5\0\17"+
    "\14\1\0\16\14\2\0\14\14\13\0\1\14\15\0\7\14\7\0\16\14\15\0\2\14\12\12\3\0"+
    "\3\14\11\0\4\14\1\0\4\14\3\0\2\14\11\0\10\14\1\0\1\14\1\0\1\14\1\0\1\14\1"+
    "\0\6\14\1\0\7\14\1\0\1\14\3\0\3\14\1\0\7\14\3\0\4\14\2\0\6\14\5\0\1\14\15"+
    "\0\1\14\2\0\1\14\4\0\1\14\2\0\12\14\1\0\1\14\3\0\5\14\6\0\1\14\1\0\1\14\1"+
    "\0\1\14\1\0\4\14\1\0\13\14\2\0\4\14\5\0\5\14\4\0\1\14\4\0\2\14\13\0\5\14\6"+
    "\0\4\14\3\0\2\14\14\0\10\14\7\0\10\14\1\0\7\14\6\0\2\14\12\0\5\14\5\0\2\14"+
    "\3\0\7\14\6\0\3\14\12\12\2\14\13\0\11\14\2\0\27\14\2\0\7\14\1\0\3\14\1\0\4"+
    "\14\1\0\4\14\2\0\6\14\3\0\1\14\1\0\1\14\2\0\5\14\1\0\12\14\12\12\5\14\1\0"+
    "\3\14\1\0\10\14\4\0\7\14\3\0\1\14\3\0\2\14\1\0\1\14\3\0\2\14\2\0\5\14\2\0"+
    "\1\14\1\0\1\14\30\0\3\14\3\0\6\14\2\0\6\14\2\0\6\14\11\0\7\14\4\0\5\14\3\0"+
    "\5\14\5\0\1\14\1\0\10\14\1\0\5\14\1\0\1\14\1\0\2\14\1\0\2\14\1\0\12\14\6\0"+
    "\12\14\2\0\6\14\2\0\6\14\2\0\6\14\2\0\3\14\3\0\14\14\1\0\16\14\1\0\2\14\1"+
    "\0\2\14\1\0\10\14\6\0\4\14\4\0\16\14\2\0\1\14\1\0\14\14\1\0\2\14\3\0\1\14"+
    "\2\0\4\14\1\0\2\14\12\0\10\14\6\0\6\14\1\0\3\14\1\0\12\14\3\0\1\14\12\0\4"+
    "\14\13\0\12\12\1\14\1\0\1\14\3\0\7\14\1\0\1\14\1\0\4\14\1\0\17\14\1\0\2\14"+
    "\14\0\3\14\4\0\2\14\1\0\1\14\20\0\4\14\10\0\1\14\13\0\10\14\5\0\3\14\2\0\1"+
    "\14\2\0\2\14\2\0\4\14\1\0\14\14\1\0\1\14\1\0\7\14\1\0\21\14\1\0\4\14\2\0\10"+
    "\14\1\0\7\14\1\0\14\14\1\0\4\14\1\0\5\14\1\0\1\14\3\0\14\14\2\0\13\14\1\0"+
    "\10\14\2\0\22\12\1\0\2\14\1\0\1\14\2\0\1\14\1\0\12\14\1\0\4\14\1\0\1\14\1"+
    "\0\1\14\6\0\1\14\4\0\1\14\1\0\1\14\1\0\1\14\1\0\3\14\1\0\2\14\1\0\1\14\2\0"+
    "\1\14\1\0\1\14\1\0\1\14\1\0\1\14\1\0\1\14\1\0\2\14\1\0\1\14\2\0\4\14\1\0\7"+
    "\14\1\0\4\14\1\0\4\14\1\0\1\14\1\0\12\14\1\0\5\14\1\0\3\14\1\0\5\14\1\0\5"+
    "\14");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\1\2\4\1\5\1\6"+
    "\1\1\17\4\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\0"+
    "\1\24\1\0\1\4\2\0\30\4\1\23\1\0\1\4"+
    "\1\5\2\4\1\25\24\4\1\23\1\0\3\4\1\26"+
    "\3\4\1\27\1\30\3\4\1\31\5\4\1\32\1\33"+
    "\1\34\1\4\1\0\17\4\1\0\1\4\1\35\3\4"+
    "\1\36\5\4\1\37\1\40\2\4\1\41\1\42\5\4"+
    "\1\43\1\4\1\44\1\45\1\4\1\46\2\4\1\47"+
    "\2\4\1\50\1\51\2\4\1\52\1\53\1\54";

  private static int [] zzUnpackAction() {
    int [] result = new int[176];
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
    "\0\0\0\55\0\132\0\207\0\264\0\341\0\u010e\0\u013b"+
    "\0\55\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249\0\u0276"+
    "\0\u02a3\0\u02d0\0\u02fd\0\u032a\0\u0357\0\u0384\0\u03b1\0\u03de"+
    "\0\u040b\0\55\0\55\0\55\0\55\0\55\0\55\0\55"+
    "\0\55\0\55\0\55\0\55\0\u0438\0\u0465\0\264\0\55"+
    "\0\u0492\0\u04bf\0\u04ec\0\u0168\0\55\0\u0519\0\u0546\0\u0573"+
    "\0\u05a0\0\u05cd\0\u05fa\0\u0627\0\u0654\0\u0681\0\u06ae\0\u06db"+
    "\0\u0708\0\u0735\0\u0762\0\u078f\0\u07bc\0\u07e9\0\u0816\0\u0843"+
    "\0\u0870\0\u089d\0\u08ca\0\u08f7\0\u0924\0\u0951\0\u097e\0\u04ec"+
    "\0\u09ab\0\u09d8\0\u010e\0\u0a05\0\u0a32\0\u0a5f\0\u0a8c\0\u0ab9"+
    "\0\u0ae6\0\u0b13\0\u0b40\0\u0b6d\0\u0b9a\0\u0bc7\0\u0bf4\0\u0c21"+
    "\0\u0c4e\0\u0c7b\0\u0ca8\0\u0cd5\0\u0d02\0\u0d2f\0\u0d5c\0\55"+
    "\0\u0d89\0\u0db6\0\u0de3\0\u0e10\0\u010e\0\u0e3d\0\u0e6a\0\u0e97"+
    "\0\u010e\0\u010e\0\u0ec4\0\u0ef1\0\u0f1e\0\u010e\0\u0f4b\0\u0f78"+
    "\0\u0fa5\0\u0fd2\0\u0fff\0\u010e\0\u010e\0\u010e\0\u102c\0\u1059"+
    "\0\u1086\0\u10b3\0\u10e0\0\u110d\0\u113a\0\u1167\0\u1194\0\u11c1"+
    "\0\u11ee\0\u121b\0\u1248\0\u1275\0\u12a2\0\u12cf\0\u12fc\0\u1329"+
    "\0\u1356\0\u010e\0\u1383\0\u13b0\0\u13dd\0\u010e\0\u140a\0\u1437"+
    "\0\u1464\0\u1491\0\u14be\0\u010e\0\u010e\0\u14eb\0\u1518\0\u010e"+
    "\0\u010e\0\u1545\0\u1572\0\u159f\0\u15cc\0\u15f9\0\u010e\0\u1626"+
    "\0\u010e\0\u010e\0\u1653\0\u010e\0\u1680\0\u16ad\0\u010e\0\u16da"+
    "\0\u1707\0\u010e\0\u010e\0\u1734\0\u1761\0\u178e\0\u010e\0\u010e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[176];
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
    "\1\2\2\3\1\4\1\2\1\5\1\2\1\6\1\7"+
    "\2\10\1\11\1\7\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\7\1\25"+
    "\1\26\1\27\1\7\1\30\1\7\1\31\2\7\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42"+
    "\1\43\1\44\56\0\2\3\55\0\1\45\1\46\50\0"+
    "\5\47\1\50\1\51\46\47\7\0\4\7\1\0\1\7"+
    "\1\0\3\7\1\52\20\7\22\0\4\7\1\0\1\7"+
    "\1\0\24\7\24\0\2\10\1\53\41\0\15\54\1\55"+
    "\37\54\7\0\4\7\1\0\1\7\1\0\1\7\1\56"+
    "\4\7\1\57\15\7\22\0\4\7\1\0\1\7\1\0"+
    "\7\7\1\60\1\61\13\7\22\0\4\7\1\0\1\7"+
    "\1\0\3\7\1\62\20\7\22\0\4\7\1\0\1\7"+
    "\1\0\21\7\1\63\2\7\22\0\4\7\1\0\1\7"+
    "\1\0\10\7\1\64\13\7\22\0\4\7\1\0\1\7"+
    "\1\0\4\7\1\65\17\7\22\0\1\7\1\66\2\7"+
    "\1\0\1\7\1\0\3\7\1\67\3\7\1\70\14\7"+
    "\22\0\1\71\3\7\1\0\1\7\1\0\24\7\22\0"+
    "\4\7\1\0\1\7\1\0\6\7\1\72\7\7\1\73"+
    "\5\7\22\0\1\7\1\74\2\7\1\0\1\7\1\0"+
    "\5\7\1\75\16\7\22\0\4\7\1\0\1\7\1\0"+
    "\3\7\1\76\4\7\1\77\13\7\22\0\4\7\1\0"+
    "\1\7\1\0\7\7\1\100\14\7\22\0\4\7\1\0"+
    "\1\7\1\0\1\101\2\7\1\102\20\7\22\0\4\7"+
    "\1\0\1\7\1\0\1\103\23\7\22\0\4\7\1\0"+
    "\1\7\1\0\7\7\1\104\14\7\13\0\1\45\1\0"+
    "\53\45\4\46\1\105\50\46\3\0\1\47\1\0\3\47"+
    "\1\106\11\0\3\47\5\0\1\47\31\0\4\7\1\0"+
    "\1\7\1\0\3\7\1\107\20\7\24\0\2\110\51\0"+
    "\4\7\1\0\1\7\1\0\2\7\1\111\21\7\22\0"+
    "\4\7\1\0\1\7\1\0\5\7\1\112\16\7\22\0"+
    "\4\7\1\0\1\7\1\0\2\7\1\113\21\7\22\0"+
    "\4\7\1\0\1\7\1\0\5\7\1\114\16\7\22\0"+
    "\4\7\1\0\1\7\1\0\15\7\1\115\6\7\22\0"+
    "\4\7\1\0\1\7\1\0\10\7\1\116\13\7\22\0"+
    "\4\7\1\0\1\7\1\0\12\7\1\117\11\7\22\0"+
    "\1\7\1\120\2\7\1\0\1\7\1\0\24\7\22\0"+
    "\4\7\1\0\1\7\1\0\15\7\1\121\6\7\22\0"+
    "\4\7\1\0\1\7\1\0\13\7\1\122\10\7\22\0"+
    "\4\7\1\0\1\7\1\0\1\7\1\123\22\7\22\0"+
    "\4\7\1\0\1\7\1\0\11\7\1\124\12\7\22\0"+
    "\1\7\1\125\2\7\1\0\1\7\1\0\24\7\22\0"+
    "\4\7\1\0\1\7\1\0\5\7\1\126\16\7\22\0"+
    "\4\7\1\0\1\7\1\0\2\7\1\127\21\7\22\0"+
    "\4\7\1\0\1\7\1\0\4\7\1\130\17\7\22\0"+
    "\1\7\1\131\2\7\1\0\1\7\1\0\24\7\22\0"+
    "\4\7\1\0\1\7\1\0\14\7\1\132\7\7\22\0"+
    "\4\7\1\0\1\7\1\0\15\7\1\133\6\7\22\0"+
    "\4\7\1\0\1\7\1\0\11\7\1\134\12\7\22\0"+
    "\4\7\1\0\1\7\1\0\6\7\1\135\15\7\22\0"+
    "\4\7\1\0\1\7\1\0\5\7\1\136\16\7\22\0"+
    "\4\7\1\0\1\7\1\0\4\7\1\137\17\7\13\0"+
    "\3\46\1\140\1\105\50\46\11\0\1\141\52\0\4\7"+
    "\1\0\1\7\1\0\15\7\1\142\6\7\22\0\4\7"+
    "\1\0\1\7\1\0\3\7\1\143\20\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\144\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\7\7\1\145\14\7\22\0\4\7"+
    "\1\0\1\7\1\0\22\7\1\146\1\7\22\0\4\7"+
    "\1\0\1\7\1\0\4\7\1\147\17\7\22\0\4\7"+
    "\1\0\1\7\1\0\3\7\1\150\20\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\151\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\15\7\1\152\6\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\153\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\154\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\5\7\1\155\16\7\22\0\4\7"+
    "\1\0\1\7\1\0\1\7\1\156\22\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\157\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\2\7\1\160\21\7\22\0\4\7"+
    "\1\0\1\7\1\0\1\161\23\7\22\0\1\162\3\7"+
    "\1\0\1\7\1\0\24\7\22\0\4\7\1\0\1\7"+
    "\1\0\7\7\1\163\14\7\22\0\4\7\1\0\1\7"+
    "\1\0\11\7\1\120\12\7\22\0\4\7\1\0\1\7"+
    "\1\0\5\7\1\164\16\7\22\0\4\7\1\0\1\7"+
    "\1\0\23\7\1\165\22\0\4\7\1\0\1\7\1\0"+
    "\20\7\1\166\3\7\22\0\4\7\1\0\1\7\1\0"+
    "\5\7\1\167\16\7\24\0\1\170\52\0\4\7\1\0"+
    "\1\7\1\0\10\7\1\171\13\7\22\0\4\7\1\0"+
    "\1\7\1\0\4\7\1\172\17\7\22\0\4\7\1\0"+
    "\1\7\1\0\23\7\1\173\22\0\4\7\1\0\1\7"+
    "\1\0\1\7\1\174\22\7\22\0\4\7\1\0\1\7"+
    "\1\0\4\7\1\175\17\7\22\0\4\7\1\0\1\7"+
    "\1\0\4\7\1\176\17\7\22\0\4\7\1\0\1\7"+
    "\1\0\14\7\1\177\7\7\22\0\4\7\1\0\1\7"+
    "\1\0\11\7\1\200\12\7\22\0\4\7\1\0\1\7"+
    "\1\0\4\7\1\201\17\7\22\0\4\7\1\0\1\7"+
    "\1\0\6\7\1\202\15\7\22\0\4\7\1\0\1\7"+
    "\1\0\15\7\1\203\6\7\22\0\4\7\1\0\1\7"+
    "\1\0\6\7\1\204\15\7\22\0\4\7\1\0\1\7"+
    "\1\0\15\7\1\205\6\7\22\0\1\7\1\206\2\7"+
    "\1\0\1\7\1\0\24\7\22\0\4\7\1\0\1\7"+
    "\1\0\22\7\1\207\1\7\24\0\1\210\52\0\4\7"+
    "\1\0\1\7\1\0\7\7\1\211\14\7\22\0\4\7"+
    "\1\0\1\7\1\0\5\7\1\212\16\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\213\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\3\7\1\214\20\7\22\0\4\7"+
    "\1\0\1\7\1\0\1\215\23\7\22\0\4\7\1\0"+
    "\1\7\1\0\13\7\1\216\10\7\22\0\4\7\1\0"+
    "\1\7\1\0\7\7\1\217\14\7\22\0\4\7\1\0"+
    "\1\7\1\0\2\7\1\220\21\7\22\0\4\7\1\0"+
    "\1\7\1\0\7\7\1\221\14\7\22\0\4\7\1\0"+
    "\1\7\1\0\13\7\1\222\10\7\22\0\4\7\1\0"+
    "\1\7\1\0\10\7\1\223\13\7\22\0\4\7\1\0"+
    "\1\7\1\0\23\7\1\224\22\0\4\7\1\0\1\7"+
    "\1\0\10\7\1\225\13\7\22\0\4\7\1\0\1\7"+
    "\1\0\15\7\1\226\6\7\22\0\4\7\1\0\1\7"+
    "\1\0\2\7\1\227\21\7\24\0\1\47\52\0\4\7"+
    "\1\0\1\7\1\0\6\7\1\230\15\7\22\0\4\7"+
    "\1\0\1\7\1\0\4\7\1\231\17\7\22\0\4\7"+
    "\1\0\1\7\1\0\4\7\1\232\17\7\22\0\4\7"+
    "\1\0\1\7\1\0\13\7\1\233\10\7\22\0\1\7"+
    "\1\234\2\7\1\0\1\7\1\0\24\7\22\0\4\7"+
    "\1\0\1\7\1\0\7\7\1\235\14\7\22\0\4\7"+
    "\1\0\1\7\1\0\12\7\1\236\11\7\22\0\4\7"+
    "\1\0\1\7\1\0\11\7\1\237\12\7\22\0\4\7"+
    "\1\0\1\7\1\0\1\7\1\240\22\7\22\0\4\7"+
    "\1\0\1\7\1\0\5\7\1\241\16\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\242\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\2\7\1\243\21\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\244\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\15\7\1\245\6\7\22\0\4\7"+
    "\1\0\1\7\1\0\12\7\1\246\11\7\22\0\4\7"+
    "\1\0\1\7\1\0\5\7\1\247\16\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\250\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\20\7\1\251\3\7\22\0\4\7"+
    "\1\0\1\7\1\0\5\7\1\252\16\7\22\0\4\7"+
    "\1\0\1\7\1\0\10\7\1\253\13\7\22\0\4\7"+
    "\1\0\1\7\1\0\6\7\1\254\15\7\22\0\4\7"+
    "\1\0\1\7\1\0\1\255\23\7\22\0\4\7\1\0"+
    "\1\7\1\0\5\7\1\256\16\7\22\0\4\7\1\0"+
    "\1\7\1\0\12\7\1\257\11\7\22\0\4\7\1\0"+
    "\1\7\1\0\11\7\1\260\12\7\13\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6075];
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

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\6\1\1\11\20\1\13\11\2\1\1\0"+
    "\1\11\1\0\1\1\2\0\1\11\30\1\1\0\31\1"+
    "\1\11\1\0\26\1\1\0\17\1\1\0\50\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[176];
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

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

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

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
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
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
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
    return zzBuffer.charAt(zzStartRead+pos);
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

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
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
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return com.intellij.psi.TokenType.BAD_CHARACTER;
            }
          case 45: break;
          case 2: 
            { return com.intellij.psi.TokenType.WHITE_SPACE;
            }
          case 46: break;
          case 3: 
            { return E_SLASH;
            }
          case 47: break;
          case 4: 
            { return E_ID;
            }
          case 48: break;
          case 5: 
            { return E_NUMBER;
            }
          case 49: break;
          case 6: 
            { return E_DOT;
            }
          case 50: break;
          case 7: 
            { return E_COLON;
            }
          case 51: break;
          case 8: 
            { return E_COMMA;
            }
          case 52: break;
          case 9: 
            { return E_EQ;
            }
          case 53: break;
          case 10: 
            { return E_CURLY_LEFT;
            }
          case 54: break;
          case 11: 
            { return E_CURLY_RIGHT;
            }
          case 55: break;
          case 12: 
            { return E_BRACKET_LEFT;
            }
          case 56: break;
          case 13: 
            { return E_BRACKET_RIGHT;
            }
          case 57: break;
          case 14: 
            { return E_PAREN_LEFT;
            }
          case 58: break;
          case 15: 
            { return E_PAREN_RIGHT;
            }
          case 59: break;
          case 16: 
            { return E_ANGLE_LEFT;
            }
          case 60: break;
          case 17: 
            { return E_ANGLE_RIGHT;
            }
          case 61: break;
          case 18: 
            { return E_COMMENT;
            }
          case 62: break;
          case 19: 
            { return E_BLOCK_COMMENT;
            }
          case 63: break;
          case 20: 
            { return E_STRING;
            }
          case 64: break;
          case 21: 
            { return E_MAP;
            }
          case 65: break;
          case 22: 
            { return E_META;
            }
          case 66: break;
          case 23: 
            { return E_BOOLEAN;
            }
          case 67: break;
          case 24: 
            { return E_NULL;
            }
          case 68: break;
          case 25: 
            { return E_ENUM;
            }
          case 69: break;
          case 26: 
            { return E_LIST;
            }
          case 70: break;
          case 27: 
            { return E_LONG_T;
            }
          case 71: break;
          case 28: 
            { return E_WITH;
            }
          case 72: break;
          case 29: 
            { return E_IMPORT;
            }
          case 73: break;
          case 30: 
            { return E_RECORD;
            }
          case 74: break;
          case 31: 
            { return E_STRING_T;
            }
          case 75: break;
          case 32: 
            { return E_DOUBLE_T;
            }
          case 76: break;
          case 33: 
            { return E_BOOLEAN_T;
            }
          case 77: break;
          case 34: 
            { return E_INTEGER_T;
            }
          case 78: break;
          case 35: 
            { return E_EXTENDS;
            }
          case 79: break;
          case 36: 
            { return E_DEFAULT;
            }
          case 80: break;
          case 37: 
            { return E_VARTYPE;
            }
          case 81: break;
          case 38: 
            { return E_OVERRIDE;
            }
          case 82: break;
          case 39: 
            { return E_ABSTRACT;
            }
          case 83: break;
          case 40: 
            { return E_NODEFAULT;
            }
          case 84: break;
          case 41: 
            { return E_NAMESPACE;
            }
          case 85: break;
          case 42: 
            { return E_SUPPLEMENT;
            }
          case 86: break;
          case 43: 
            { return E_POLYMORPHIC;
            }
          case 87: break;
          case 44: 
            { return E_SUPPLEMENTS;
            }
          case 88: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
