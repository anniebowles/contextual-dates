import gov.va.vinci.leo.regex.types.RegularExpressionType

/* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
name = "DateAnnotator"

configuration {
    /* All configuration for this annotator. */
    defaults {
        /* Global for all configurations below if a property specified here is not overridden in a section below. */
        outputType = RegularExpressionType.class.canonicalName
        case_sensitive = false
        matchedPatternFeatureName = "pattern"
        concept_feature_name = "concept"
        groupFeatureName = "group"
    }
    /* An arbitrary name for this set of patterns/config. */


    "exact_date" {
        expressions = [
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\.?\\s{0,50}\\d{1,2}\\p{Punct}\\s{0,50}\\d{1,4}',
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\.?\\s{0,50}\\d{1,2}\\p{Punct}?\\s{1,50}\\d{2,4}',
                // 28 SEPT 2020
                '[1-3][0-9]\\s{0,50}(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\s{0,50}\\d{4}',
                '\\d\\d?\\s*(jan(uary)?|feb(ruary)?|mar(ch)?|apr(il)?|may|jun(e)?|jul(y)?|aug(ust)?|sept(ember)?|sep(tember)?|oct(ober)?|nov(ember)?|dec(ember)?)\\b',
                '(?<=in {0,30})(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\.?\\s{0,50}\\d{1,2}\\p{Punct}?\\s{1,50}\\d{1,4}',
                //March 14
                '(?<=on {0,30})(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\,?\\s*\\d{1,4}',
                '(?<=on {0,30})(jan(uary)?|feb(ruary)?|mar(ch)?|apr(il)?|may|jun(e)?|jul(y)?|aug(ust)?|sept(ember)?|sep(tember)?|oct(ober)?|nov(ember)?|dec(ember)?)\\s*\\d\\d?',
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\s*\\d\\d',
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\s*\\d',
                'August\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'February\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'Mar\\s{1,50}\\d+\\s{1,50}\\d+',
                'November\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'January\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'October\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'June\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'Nov\\s{1,50}\\d+\\s{1,50}\\d+',
                'Aug\\s{1,50}\\d+\\s{1,50}\\d+',
                '\\d{1,2}-\\d{1,2}-\\d{4}\\b',  //10-10-2020
                '\\d{1,2}-\\d{1,2}-\\d{1,2}\\b',  //10-10-20
                '\\d{4}-\\d{1,2}-\\d{1,2}\\b', //separate so it doesn't capture SSNs
                '\\d{1,4}/\\d{1,2}/\\d{1,4}\\b', //10/10/2020
                '\\d{1,4}\\.\\d{1,2}\\.\\d{1,4}\\b', //10.08.2020
                '\\(\\d{1,4}(-|/)\\d{1,2}(-|/)\\d{1,4}\\)',
                'Sep\\s{1,50}\\d+\\s{1,50}\\d+',
                'December\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'May\\s{1,50}\\d+\\p{Punct}?\\s{1,50}\\d+',
                'Apr\\s{1,50}\\d+\\s{1,50}\\d+',
                'September\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'April\\s{1,50}\\d+\\p{Punct}\\s{1,50}\\d+',
                'Oct\\s{1,50}\\d+\\s{1,50}\\d+',
                '(?<=on {0,30})\\d+\\p{Punct}\\d+\\p{Punct}\\d+',
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec),?\\s*\\d{4}',
        ]
        concept_feature_value = "ExactDate:mm-dd-yyyy"
        outputType =  	"gov.va.vinci.covtest.types.covDate"
    }

    "exact_datetime" {
        expressions = [
                //Aug 14, 2020@20:21
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\.?\\s{0,50}\\d{1,2}\\p{Punct}?\\s{1,50}\\d{1,4}@\\d{2}\\:\\d{2}',
                // 11/21/2000 14:48
                "\\d{1,2}[/-]\\d{1,2}[/-]\\d{2,4} +\\d{1,2}:\\d{2}",
                //  2020-04-23 13:43
                "\\d{4}[/-]\\d{1,2}[/-]\\d{1,2} +\\d{1,2}:\\d{2}",
                // JUL 21, 2009@08:31:41
                // NOV 10,2008@09:43:25
                // JUN 18, 2009@07:34:28
                // Mar 19,2007@08:00
                "(\\()?\\b\\w{2,4} *\\d+, *\\d+@\\d{2}:\\d{2}(:\\d{2})?(\\))?",
                // 11/10/08 @ 1355
                "\\b\\d{1,2}/\\d{1,2}/\\d\\d(\\d\\d)? *@ *\\d\\d:?\\d\\d",
                // 10/19/2006 09:13 AM
                "\\b\\d{1,2}/\\d{1,2}/\\d\\d(\\d\\d)?( *@)? *\\d\\d:?\\d\\d",
                "\\d+\\/\\d+\\/\\d+\\s+\\d+:\\d+(:\\d+)?",
                // 09/02@17:05
                "\\d+\\/\\d+ *@ *\\d+:\\d+"

        ]
        concept_feature_value = "ExactDate:mm-dd-yyyy@tttt"
        outputType =  	"gov.va.vinci.covtest.types.covDate"
    }

    "partial_date" {
        expressions = [
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec),?\\s*\\d{4}',
                '\\d{1,2}/\\d{1,4}\\b',

        ]
        concept_feature_value = "PartialDate:mm-yyyy"
        outputType =  	"gov.va.vinci.covtest.types.covDate"
    }

    "partial_date" {
        expressions = [
                //month alone //not common
                '\\b(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\b',

        ]
        concept_feature_value = "PartialDate:mm"
        outputType =  	"gov.va.vinci.covtest.types.covDate"
    }

    "partial_date" {
        expressions = [

                //4 March
                '\\d\\s{0,50}(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)',//\\b',
                //14 March
                '[1-3][0-9]\\s{0,50}(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)',//\\b',
                '\\d+ *(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)',//\\b',
                "\\d+ *(jan\\w*|feb\\w*|mar\\w*|apr\\w*|may\\w*|jun\\w*|jul\\w*|aug\\w*|sep\\w*|oct\\w*|nov\\w*|dec\\w*)",
                '(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec) *\\d+',


        ]
        concept_feature_value = "PartialDate:mm-dd"
        outputType =  	"gov.va.vinci.covtest.types.covDate"
    }

    "partial_date" {
        expressions = [
                //3/14
                '\\b0?[1-9](\\|-|/)\\d\\d?\\b'
                //11/18 first digit is in teens
                ,'\\b1\\d((\\|-|/))[1-3]?\\d\\b'
                //12-07'
                ,'\\b[10-12](\\|-|/)\\d\\d\\b'
                ,'\\b\\d\\d(\\|-|/)[10-12]\\b'
                //on 4/14 - searches for the 'on', but doesn't output it
                ,'(?<=on {0,30})\\d+\\p{Punct}\\d+'
                //07/2006'
                ,'\\d?\\d(\\|-|/)\\d\\d\\d\\d'

        ]
        concept_feature_value = "PartialDate:mm-dd"
        outputType =  	"gov.va.vinci.covtest.types.ShortDate"
    }

    "relative_date" {
        expressions = [
                'today',
                'this\\s*date',
                '(earlier )?this\\s*morning',
                'yesterday',
                '\\d\\s*(week|wk|day)s?\\s*ago',
                '\\ba\\s*(week|wk|day)\\s*ago', //a week ago
                'last\\s*night',
                '(last|this)\\s*week',
                '(last|this)\\s*(monday|tuesday|wednesday|thursday|friday|saturday|sunday)', //not common
                'monday',     //abbreviated days likely too imprecise
                'tuesday',
                'wednesday',
                'thursday',
                'friday',
                'saturday',
                'sunday',
                '(last|this)\\s*(january|february|march|april|may|june|july|august|september|october|november|december|jan|feb|mar|apr|may|jun|jul|aug|sept|sep|oct|nov|dec)\\b',
                '(within|w/in)\\s*the\\s*last\\s*24\\s*(hours|hrs)'

        ]
        concept_feature_value = "RelativeDate"
        outputType = "gov.va.vinci.covtest.types.Temporality"
    }
}
