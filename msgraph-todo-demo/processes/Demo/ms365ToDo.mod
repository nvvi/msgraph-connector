[Ivy]
17F6A07FDC97B7A5 9.3.1 #module
>Proto >Proto Collection #zClass
mo0 ms365ToDo Big #zClass
mo0 B #cInfo
mo0 #process
mo0 @AnnotationInP-0n ai ai #zField
mo0 @TextInP .type .type #zField
mo0 @TextInP .processKind .processKind #zField
mo0 @TextInP .xml .xml #zField
mo0 @TextInP .responsibility .responsibility #zField
mo0 @StartRequest f0 '' #zField
mo0 @EndTask f1 '' #zField
mo0 @PushWFArc f2 '' #zField
>Proto mo0 mo0 ms365ToDo #zField
mo0 f0 outLink start.ivp #txt
mo0 f0 inParamDecl '<> param;' #txt
mo0 f0 requestEnabled true #txt
mo0 f0 triggerEnabled false #txt
mo0 f0 callSignature start() #txt
mo0 f0 caseData businessCase.attach=true #txt
mo0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start.ivp</name>
    </language>
</elementInfo>
' #txt
mo0 f0 @C|.responsibility Everybody #txt
mo0 f0 81 49 30 30 -22 17 #rect
mo0 f1 337 49 30 30 0 15 #rect
mo0 f2 111 64 337 64 #arcP
>Proto mo0 .type com.axonivy.connector.office365.msgraph.todo.demo.Data #txt
>Proto mo0 .processKind NORMAL #txt
>Proto mo0 0 0 32 24 18 0 #rect
>Proto mo0 @|BIcon #fIcon
mo0 f0 mainOut f2 tail #connect
mo0 f2 head f1 mainIn #connect
