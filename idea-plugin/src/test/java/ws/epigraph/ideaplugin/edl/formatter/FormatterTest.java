/*
 * Copyright 2016 Sumo Logic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.epigraph.ideaplugin.edl.formatter;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class FormatterTest extends LightCodeInsightFixtureTestCase {
  @Override
  protected String getTestDataPath() {
    return "src/test/resources/testData/formatting";
  }

  public void testFormatter() {
    myFixture.configureByFile("FormatterTest.epigraph");
//    String psiDump = DebugUtil.psiToString(myFixture.getFile(), true, false).trim();
//    System.out.println(psiDump);

    new WriteCommandAction.Simple<Void>(getProject()) {
      @Override
      protected void run() throws Throwable {
        CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
      }
    }.execute();

    myFixture.checkResultByFile("FormatterTest-out.epigraph");
  }
}
