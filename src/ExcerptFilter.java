public class ExcerptFilter implements TokenFilter
{
    private boolean started;

    private ExcerptFilter()
    {
        started = true;
    };

    public static TokenFilter factory(String startTag, String stopTag)
    {
        TokenFilter filter;

        if(startTag == null && stopTag == null || startTag.trim().length() < 1 && stopTag.trim().length() < 1)
        {
            filter = new ExcerptFilter();	                // no start/stop tags
        }
        else if(startTag == null || startTag.length() < 1)
        {
            filter = new ExcerptFilterStopOnly(stopTag);	// only stopTag
        }
        else if(stopTag == null || stopTag.length() < 1)
        {
            filter = new ExcerptFilterStartOnly(startTag);	// only startTag
        }
        else
        {
            filter = new ExcerptFilterStartStop(startTag, stopTag);
        }

        return filter;
    }

    // loop through a text file and only accept when the startTag is true
    // end when the stopTag is true
    public boolean accept(String token)
    {
        boolean result;

        result = false;

        if(isStopTag(token))
        {
            stop();
            result = !isStopTag(token) && started == true;
        }
        else if(isStartTag(token) || started == true)	// if finds stopTag, return false and end program
        {
            start();
            result = !isStartTag(token) && started == true;
        }

        return result;
    }

    public boolean isStartTag(String token)
    {
        boolean result;
        result = false;
        return result;
    }

    public boolean isStopTag(String token)
    {
        boolean result;
        result = false;
        return result;
    }

    public void start() { this.started = true; }
    public void stop() { this.started = false; }

    public boolean started() { return this.started; }

    private static abstract class ExcerptFilterStartOrStop extends ExcerptFilter
    {
        private String tag;

        public ExcerptFilterStartOrStop(String tag)
        {
            this.tag = tag;
        }

        public boolean isTag(String tag)
        {
            return this.tag.equals(tag);
        }
    }

    private static class ExcerptFilterStartOnly extends ExcerptFilterStartOrStop
    {
        public ExcerptFilterStartOnly(String startTag)
        {
            super(startTag);    // tag that starts accepting words
            stop();
        }

        @Override
        public boolean isStartTag(String token)
        {
            return super.isTag(token);
        }
    }

    private static class ExcerptFilterStopOnly extends ExcerptFilterStartOrStop
    {
        public ExcerptFilterStopOnly(String stopTag)
        {
            super(stopTag);
            start();
        }

        @Override
        public boolean isStopTag(String token)
        {
            return super.isTag(token);
        }
    }

    private static class ExcerptFilterStartStop extends ExcerptFilterStartOnly
    {
        private String stopTag;

        public ExcerptFilterStartStop(String startTag, String stopTag)
        {
            super(startTag);
            this.stopTag = stopTag;
        }

        @Override
        public boolean isStopTag(String token)
        {
            return stopTag.equals(token);
        }
    }
}