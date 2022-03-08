[Ivy]
17F6A0898D9B00FC 9.3.1 #module
>Proto >Proto Collection #zClass
mr0 ms365Calendar Big #zClass
mr0 B #cInfo
mr0 #process
mr0 @AnnotationInP-0n ai ai #zField
mr0 @TextInP .type .type #zField
mr0 @TextInP .processKind .processKind #zField
mr0 @TextInP .xml .xml #zField
mr0 @TextInP .responsibility .responsibility #zField
mr0 @StartRequest f0 '' #zField
mr0 @EndTask f1 '' #zField
mr0 @PushWFArc f2 '' #zField
>Proto mr0 mr0 ms365Calendar #zField
mr0 f0 outLink start.ivp #txt
mr0 f0 inParamDecl '<> param;' #txt
mr0 f0 requestEnabled true #txt
mr0 f0 triggerEnabled false #txt
mr0 f0 callSignature start() #txt
mr0 f0 caseData businessCase.attach=true #txt
mr0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
mr0 f0 @C|.responsibility Everybody #txt
mr0 f0 81 49 30 30 -22 17 #rect
mr0 f1 337 49 30 30 0 15 #rect
mr0 f2 111 64 337 64 #arcP
>Proto mr0 .type msgraph.calendar.demo.Data #txt
>Proto mr0 .processKind NORMAL #txt
>Proto mr0 0 0 32 24 18 0 #rect
>Proto mr0 @|BIcon #fIcon
mr0 f0 mainOut f2 tail #connect
mr0 f2 head f1 mainIn #connect
