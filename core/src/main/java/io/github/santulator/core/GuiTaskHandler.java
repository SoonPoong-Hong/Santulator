/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.santulator.core;

public interface GuiTaskHandler {
    void executeInBackground(Runnable task);

    void executeOnGuiThread(Runnable task);

    void pauseThenExecuteOnGuiThread(Runnable task);
}
