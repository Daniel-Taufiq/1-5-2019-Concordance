public class ExcerptFilter implements TokenFilter
{
    /*
        Daniel Taufiq
        5/1/2019
        Dr. Jarvis

        Class Description:
            A factory pattern that models a start and stop
            zone where words will be accepted

        Static Classes:
            private static abstract class ExcerptFilterStartOrStop extends ExcerptFilter
                allows for subclasses to push state here and
                model three types of filters.
            private static class ExcerptFilterStartOnly extends ExcerptFilterStartOrStop
                models a tag but no stop tag. Everything after
                the start tag is accepted.
            private static class ExcerptFilterStopOnly extends ExcerptFilterStartOrStop
                models a stop tag but no start tag. Everything
                up to the stop tag is accepted.
            private static class ExcerptFilterStartStop extends ExcerptFilterStartOnly
                models a start tag and end tag. Accepting takes
                place after the start tag and before the end tag.

         Instance Variable:
            private boolean started
                will always start accepting unless override by a static
                class constructor.

         Constructor:
            public static TokenFilter factory(String startTag, String stopTag)
                a factory pattern design that creates an object
                based on the actual parameters.

         Methods:
             public boolean accept(String token)
                implemented from TokenFilter, accepts words based
                on start and stop tag.
             public boolean isStartTag(String token)
                set to false since the default object accepts
                everything.
             public boolean isStopTag(String token)
                set to false since the default object accepts
                everything.
             public void start()
                sets the instance variable to true
             public void stop()
                sets the instance variable to false
             public boolean started()
                returns instance variable
     */

    private boolean started;

    private ExcerptFilter() { started = true; };

    public static TokenFilter factory(String startTag, String stopTag)
    {
        TokenFilter filter;

        if(startTag == null && stopTag == null || startTag.trim().length() < 1 && stopTag.trim().length() < 1)
        {
            filter = new ExcerptFilter();	// no start/stop tags
        }

        else if(startTag == null || startTag.trim().length() < 1)
        {
            filter = new ExcerptFilterStopOnly(stopTag);	// only stopTag
        }
        else if(stopTag == null || stopTag.trim().length() < 1)
        {
            filter = new ExcerptFilterStartOnly(startTag);	// only startTag
        }
        else
        {
            filter = new ExcerptFilterStartStop(startTag, stopTag);
        }

        return filter;
    }

    @Override
    public boolean accept(String token)
    {
        boolean result;

        result = false;

        if(isStopTag(token))
        {
            stop();
            result = !isStopTag(token) && started == true;
        }
        else if(isStartTag(token) || started == true)
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

        public ExcerptFilterStartOrStop(String tag) { this.tag = tag; }

        public boolean isTag(String tag) { return this.tag.equals(tag); }
    }

    private static class ExcerptFilterStartOnly extends ExcerptFilterStartOrStop
    {
        public ExcerptFilterStartOnly(String startTag)
        {
            super(startTag);
            stop();
        }

        @Override
        public boolean isStartTag(String token) { return super.isTag(token); }
    }

    private static class ExcerptFilterStopOnly extends ExcerptFilterStartOrStop
    {
        public ExcerptFilterStopOnly(String stopTag)
        {
            super(stopTag);
            start();
        }

        @Override
        public boolean isStopTag(String token) { return super.isTag(token); }
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
        public boolean isStopTag(String token) { return stopTag.equals(token); }
    }
}