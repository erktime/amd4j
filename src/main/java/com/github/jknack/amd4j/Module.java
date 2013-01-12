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

import java.util.LinkedHashSet;
import java.util.Set;

class Module {

  public final String name;

  public final StringBuilder content;

  public final Set<String> dependencies = new LinkedHashSet<String>();

  public Module(final String name, final StringBuilder content) {
    this.name = name;
    this.content = content;
  }

  @Override
  public String toString() {
    return name;
  }
}
