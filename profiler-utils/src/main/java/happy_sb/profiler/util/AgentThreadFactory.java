package happy_sb.profiler.util;

import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

public final class AgentThreadFactory implements ThreadFactory {
  public static final ThreadGroup AGENT_THREAD_GROUP = new ThreadGroup("one-profiler");

  // known agent threads
  public enum AgentThread {

    TRACE_METHOD_TRANSFORMER("trace-method-transformer");

    public final String threadName;

    AgentThread(final String threadName) {
      this.threadName = threadName;
    }

    public static Set<String> threadNames = new HashSet<>();

    static {
      for (AgentThread value : AgentThread.values()) {
        threadNames.add(value.threadName);
      }
    }

    public static Set<String> getThreadNames() {
      return threadNames;
    }
  }

  private final AgentThread agentThread;

  public AgentThreadFactory(final AgentThread agentThread) {
    this.agentThread = agentThread;
  }

  @Override
  public Thread newThread(final Runnable runnable) {
    return newAgentThread(agentThread, runnable);
  }

  public static Thread newAgentThread(final AgentThread agentThread, final Runnable runnable) {
    final Thread thread = new Thread(AGENT_THREAD_GROUP, runnable, agentThread.threadName);
    thread.setDaemon(true);
    thread.setContextClassLoader(null);
    thread.setUncaughtExceptionHandler(
        new Thread.UncaughtExceptionHandler() {
          @Override
          public void uncaughtException(final Thread thread, final Throwable e) {
            LoggerFactory.getLogger(runnable.getClass())
                .error("Uncaught exception in {}", agentThread.threadName, e);
          }
        });
    return thread;
  }
}
