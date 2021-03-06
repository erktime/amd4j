/**
 * Copyright (c) 2013 Edgar Espina
 *
 * This file is part of amd4j (https://github.com/jknack/amd4j)
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
package com.github.jknack.amd4j;

import static org.apache.commons.io.FilenameUtils.getName;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.Validate.isTrue;

import java.io.File;
import java.io.IOException;

import com.google.javascript.jscomp.CompilationLevel;

/**
 * Optimize an AMD script file.
 *
 * @goal optimize
 * @phase prepare-package
 * @since 0.1.0
 */
public class OptimizeMojo extends Amd4jMojo {

  static {
    Minifier.register("closure.white", new ClosureMinifier(CompilationLevel.WHITESPACE_ONLY));
    Minifier.register("closure", new ClosureMinifier(CompilationLevel.SIMPLE_OPTIMIZATIONS));
    Minifier.register("closure.advanced", new ClosureMinifier(
        CompilationLevel.ADVANCED_OPTIMIZATIONS));
  }

  /**
   * The output's file.
   *
   * @parameter
   *   expression="${project.build.directory}/${project.build.finalName}/${script.name}.opt.js"
   * @required
   */
  private String out;

  /**
   * The finale output.
   */
  private String fout;

  /**
   * Inline text in the final output. Default: true.
   *
   * @parameter
   */
  private Boolean inlineText;

  /**
   * Remove "useStrict"; statement from output.
   *
   * @parameter
   */
  private Boolean useStrict;

  /**
   * An optional build profile.
   *
   * @parameter
   */
  private String buildFile;

  /**
   * The minifier/optimizer to use. Default is: none.
   *
   * @parameter
   */
  private String optimize;

  @Override
  public void doExecute(final Amd4j amd4j, final Config config) throws IOException {
    isTrue(config.getOut() != null, "The following option is required: %s", "out");
    isTrue(!isEmpty(config.getBaseUrl()), "The following option is required: %s", "baseUrl");
    dprintf("optimizing %s...", config.getName());
    long start = System.currentTimeMillis();
    Module module = amd4j.optimize(config);
    long end = System.currentTimeMillis();
    dprintf("result:\n%s", module.toStringTree().trim());
    printf("found %s dependencies for %s -> %s took %sms", module.getDependencies(true).size(),
        config.getName(), fout, end - start);
  }

  @Override
  protected Config newConfig() throws IOException {
    if (isEmpty(buildFile)) {
      return super.newConfig();
    } else {
      return Config.parse(new File(buildFile));
    }
  }

  @Override
  protected Config merge(final String name, final Config config) throws IOException {
    super.merge(name, config);
    if (!isEmpty(this.out)) {
      fout = this.out.replace("${script.name}", getName(name));
      File out = new File(fout);
      out.getParentFile().mkdirs();
      config.setOut(out);
    }
    if (inlineText != null) {
      config.setInlineText(inlineText.booleanValue());
    }
    if (useStrict != null) {
      config.setUseStrict(useStrict.booleanValue());
    }
    if (optimize != null) {
      config.setOptimize(optimize);
    }
    return config;
  }

  @Override
  protected String header(final String name) {
    return "optimization of " + name;
  }
}
